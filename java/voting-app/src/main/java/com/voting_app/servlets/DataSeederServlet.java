package com.voting_app.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voting_app.dao.VotePollListDAO;
import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.PoliticalParty;
import com.voting_app.servlets.constants.IServletConstants;
import com.voting_app.util.cookies.CookieManager;
import com.voting_app.util.cookies.VotingPollCookieHandler;

/**
 ********************************************************************
 * Servlet that provides a post method for seeding the cookies with default/dummy data
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class DataSeederServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataSeederServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VotingPollCookieHandler cookieHandler = new VotingPollCookieHandler();
		IVotePollDAO dao = new VotePollListDAO();
		
		PoliticalParty pbd = dao.getPoliticalPartyDAO().addPoliticalParty("People Before Dogfish");
		PoliticalParty urp = dao.getPoliticalPartyDAO().addPoliticalParty("Underwater Republican Party");
		PoliticalParty tislp = dao.getPoliticalPartyDAO().addPoliticalParty("The I Still Live Party");
		PoliticalParty uu = dao.getPoliticalPartyDAO().addPoliticalParty("United Utopians");
		PoliticalParty bbbp = dao.getPoliticalPartyDAO().addPoliticalParty("Beware Big Brother Party");
		
		dao.getCandidateDAO().addCandidate("Kathy Fishman", pbd);
		dao.getCandidateDAO().addCandidate("Craig Heaney", urp);
		dao.getCandidateDAO().addCandidate("Andrew Ryan", tislp);
		dao.getCandidateDAO().addCandidate("George O' Carroll", uu);
		dao.getCandidateDAO().addCandidate("Alice Orwell", bbbp);
		
		CookieManager cookieManager = new CookieManager();
		cookieManager.addCookies(cookieHandler.getCookies(dao), response);
		response.sendRedirect(request.getHeader(IServletConstants.REFERER_HEADER));
	}

}
