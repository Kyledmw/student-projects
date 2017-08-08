package com.voting_app.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.PoliticalParty;
import com.voting_app.servlets.constants.IServletConstants;
import com.voting_app.util.cookies.CookieManager;
import com.voting_app.util.cookies.VotingPollCookieHandler;

/**
 ********************************************************************
 * Servlet that provides a a post method for the delete political party form
 * 
 * After it has deleted the political party it redirects back 
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class DeletePoliticalPartiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private static final String POLITICAL_PARTY_ID_KEY = "p_id";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePoliticalPartiesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		VotingPollCookieHandler cookieHandler = new VotingPollCookieHandler();
		IVotePollDAO dao = cookieHandler.getDAO(cookies);
		PoliticalParty pparty = dao.getPoliticalPartyDAO().getPoliticalParty(Integer.parseInt(request.getParameter(POLITICAL_PARTY_ID_KEY)));
		dao.removePoliticalParty(pparty);
		CookieManager cookieManager = new CookieManager();
		cookieManager.addCookies(cookieHandler.getCookies(dao), response);
		response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
	}

}
