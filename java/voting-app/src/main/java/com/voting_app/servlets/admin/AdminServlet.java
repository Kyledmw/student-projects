package com.voting_app.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.servlets.constants.IServletConstants;
import com.voting_app.util.cookies.VotingPollCookieHandler;


/**
 ********************************************************************
 * Servlet that provides a get method for the admin page.
 * 
 * It returns admin_page.jsp with relevant data.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ADMIN_JSP = "/admin_page.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		request.setAttribute(IServletConstants.POLITICAL_PARTY_LIST_KEY, dao.getPoliticalPartyDAO().getPoliticalParties());
		
		RequestDispatcher dispatcher =
			       getServletContext().getRequestDispatcher(ADMIN_JSP);
		
		dispatcher.include(request, response);
		request.getSession().removeAttribute(IServletConstants.ERRORS_KEY);
	}
}
