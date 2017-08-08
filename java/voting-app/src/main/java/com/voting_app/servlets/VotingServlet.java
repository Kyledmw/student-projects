package com.voting_app.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.Candidate;
import com.voting_app.servlets.constants.IServletConstants;
import com.voting_app.servlets.validation.forms.IFormValidator;
import com.voting_app.servlets.validation.forms.VoteFormValidator;
import com.voting_app.util.cookies.CookieManager;
import com.voting_app.util.cookies.VotingPollCookieHandler;

/**
 ********************************************************************
 * Servlet that provides a http methods for serving up the voting page and handling votes
 * 
 * Get: returns voting_page.jsp with relevant data.
 * 
 * Post: validates the form 
 * If its valid it updates the DAO and redirects with a success message
 * If its invalid it redirects with a list of error messages
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class VotingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VOTING_JSP = "/voting_page.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		VotingPollCookieHandler cookieHandler = new VotingPollCookieHandler();
		IVotePollDAO dao = cookieHandler.getDAO(cookies);

		request.setAttribute(IServletConstants.CANDIDATE_LIST_KEY, dao.getCandidateDAO().getCandidates());
		RequestDispatcher dispatcher =
			       getServletContext().getRequestDispatcher(VOTING_JSP);
		
		dispatcher.include(request, response);
		request.getSession().removeAttribute(IServletConstants.SUCCESS_KEY);
		request.getSession().removeAttribute(IServletConstants.ERRORS_KEY);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		VotingPollCookieHandler cookieHandler = new VotingPollCookieHandler();
		IVotePollDAO dao = cookieHandler.getDAO(cookies);
		IFormValidator validator = new VoteFormValidator();
		List<String> errors = validator.validate(dao, request);
		if(errors.isEmpty()) {
			Map<Candidate, Integer> vote = new HashMap<Candidate, Integer>();
			for(Candidate cur: dao.getCandidateDAO().getCandidates()) {
				String rankStr = request.getParameter("" + cur.getId());
				if(rankStr != null && !rankStr.equals("")) {
					Integer rank = Integer.parseInt(rankStr);
					vote.put(cur, rank);
				}
			}
			dao.getCandidateVotesDAO().addVote(vote);
			dao.incrementVotes();
			CookieManager cookieManager = new CookieManager();
			cookieManager.addCookies(cookieHandler.getCookies(dao), response);
			request.getSession().setAttribute(IServletConstants.SUCCESS_KEY, IServletConstants.VOTING_SUCCESS_MESSAGE);
			response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
		} else {
			dao.incrementInvalidVotes();
			CookieManager cookieManager = new CookieManager();
			cookieManager.addCookies(cookieHandler.getCookies(dao), response);
			request.getSession().setAttribute(IServletConstants.ERRORS_KEY, errors);
			response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
		}
	}

}
