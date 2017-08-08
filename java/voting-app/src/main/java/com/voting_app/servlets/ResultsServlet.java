package com.voting_app.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.CandidateVotes;
import com.voting_app.util.comparator.CandidateVotesComparator;
import com.voting_app.util.cookies.VotingPollCookieHandler;

/**
 ********************************************************************
 * Servlet that provides a get method for the results page.
 * 
 * It returns result_page.jsp with relevant data.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String RESULTS_JSP = "/results_page.jsp";
	
	private static final String CANDIDATE_VOTES_LIST_KEY = "candidateVotesList";
	private static final String CANDIDATE_AMOUNT_KEY = "candidateAmount";
	private static final String VOTING_POLL_KEY = "votingPoll";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		VotingPollCookieHandler cookieHandler = new VotingPollCookieHandler();
		IVotePollDAO dao = cookieHandler.getDAO(cookies);
		int candidateAmount = dao.getCandidateDAO().getCandidates().size();
		CandidateVotesComparator comparator = new CandidateVotesComparator(candidateAmount);
		List<CandidateVotes> candidateVotes = dao.getCandidateVotesDAO().getCandidateVotes();
		Collections.sort(candidateVotes, comparator);
		
		request.setAttribute(CANDIDATE_VOTES_LIST_KEY, candidateVotes);
		request.setAttribute(CANDIDATE_AMOUNT_KEY, candidateAmount);
		request.setAttribute(VOTING_POLL_KEY, dao.getVotingPoll());
		RequestDispatcher dispatcher =
			       getServletContext().getRequestDispatcher(RESULTS_JSP);
		
		dispatcher.include(request, response);
	}
}
