package com.voting_app.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.servlets.constants.IServletConstants;
import com.voting_app.servlets.validation.forms.AddCandidateFormValidator;
import com.voting_app.servlets.validation.forms.IFormValidator;
import com.voting_app.util.cookies.CookieManager;
import com.voting_app.util.cookies.VotingPollCookieHandler;

/**
 ********************************************************************
 * Servlet that provides a a post method for the add candidate form
 * 
 * It  validates the form 
 * If its valid it updates the DAO and redirects with a success message
 * If its invalid it redirects with a list of error messages
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class AddCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CANDIDATE_NAME = "c_name";
	private static final String POLITICAL_PARTY_ID = "political_party";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCandidateServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		VotingPollCookieHandler cookieHandler = new VotingPollCookieHandler();
		IVotePollDAO dao = cookieHandler.getDAO(cookies);
		IFormValidator validator = new AddCandidateFormValidator();
		List<String> errors = validator.validate(dao, request);
		if(errors.isEmpty()) {
			int ppartyId = Integer.parseInt(request.getParameter(POLITICAL_PARTY_ID));
			dao.getCandidateDAO().addCandidate(request.getParameter(CANDIDATE_NAME), dao.getPoliticalPartyDAO().getPoliticalParty(ppartyId));
			CookieManager cookieManager = new CookieManager();
			cookieManager.addCookies(cookieHandler.getCookies(dao), response);
			response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
		} else {
			request.getSession().setAttribute(IServletConstants.ERRORS_KEY, errors);
			response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
		}
	}

}
