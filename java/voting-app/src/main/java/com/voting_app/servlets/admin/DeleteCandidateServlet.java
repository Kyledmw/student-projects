package com.voting_app.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.Candidate;
import com.voting_app.servlets.constants.IServletConstants;
import com.voting_app.util.cookies.CookieManager;
import com.voting_app.util.cookies.VotingPollCookieHandler;

/**
 ********************************************************************
 * Servlet that provides a a post method for the delete candidate form
 * 
 * After it has deleted the candidate it redirects back 
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class DeleteCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CANDIDATE_ID_KEY = "c_id";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCandidateServlet() {	
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		VotingPollCookieHandler cookieHandler = new VotingPollCookieHandler();
		IVotePollDAO dao = cookieHandler.getDAO(cookies);
		Candidate candidate = dao.getCandidateDAO().getCandidate(Integer.parseInt(request.getParameter(CANDIDATE_ID_KEY)));
		dao.removeCandidate(candidate);
		CookieManager cookieManager = new CookieManager();
		cookieManager.addCookies(cookieHandler.getCookies(dao), response);
		response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
		
	}

}
