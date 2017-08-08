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
import com.voting_app.servlets.validation.forms.AddPoliticalPartyFormValidator;
import com.voting_app.servlets.validation.forms.IFormValidator;
import com.voting_app.util.cookies.CookieManager;
import com.voting_app.util.cookies.VotingPollCookieHandler;

/**
 ********************************************************************
 * Servlet that provides a a post method for the add political party form
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
public class AddPoliticalPartiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String POLITICAL_PARTY_NAME = "p_name";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPoliticalPartiesServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		VotingPollCookieHandler cookieHandler = new VotingPollCookieHandler();
		IVotePollDAO dao = cookieHandler.getDAO(cookies);
		IFormValidator validator = new AddPoliticalPartyFormValidator();
		List<String> errors = validator.validate(dao, request);
		if(errors.isEmpty()) {
			dao.getPoliticalPartyDAO().addPoliticalParty(request.getParameter(POLITICAL_PARTY_NAME ));
			CookieManager cookieManager = new CookieManager();
			cookieManager.addCookies(cookieHandler.getCookies(dao), response);
			response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
		} else {
			request.getSession().setAttribute(IServletConstants.ERRORS_KEY, errors);
			response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
		}
		
	}

}
