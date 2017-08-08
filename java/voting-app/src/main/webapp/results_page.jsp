<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw=="
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha256-KXn5puMvxCw+dAYznun+drMdG1IFl3agK0p/pqT9KAo= sha512-2e8qq0ETcfWRI4HJBzQiA3UoyFk6tbNyG+qSaIBZLyW9Xf3sWZHN/lxe9fTh1U45DpPf07yj94KsUHHWe4Yk1A=="
	crossorigin="anonymous"></script>
<title>Results Page</title>
</head>

<body>
	<jsp:useBean id="VoteCalculator"
		class="com.voting_app.util.calculators.VoteCalculatorBean">
		<jsp:setProperty name="VoteCalculator" property="totalCandidates"
			value="${candidateAmount}" />
	</jsp:useBean>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						<b>Poll Results</b>
					</div>
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Candidate</th>
									<th>Political Party</th>
									<th>Points Scored</th>
									<th>Percentage of Rank 1 Votes</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="candidateVotes" items="${candidateVotesList}">
									<jsp:setProperty name="VoteCalculator" property="votesByRank"
										value="${candidateVotes.votesByRank}" />
									<%
										VoteCalculator.calculateVotes();
									%>
									<tr>
										<td><c:out value="${candidateVotes.candidate.name}" /></td>
										<td><c:out
												value="${candidateVotes.candidate.politicalParty.name}" />
										</td>
										<td><jsp:getProperty name="VoteCalculator"
												property="totalVotes" /></td>
										<td><kw:Percent
												amount="${candidateVotes.getRankOneVotes()}"
												total="${votingPoll.validVotes}"></kw:Percent></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-4 text-center">
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-info">
							<p>
								There are
								<c:out
									value="${votingPoll.invalidVotes + votingPoll.validVotes}" />
								votes cast.
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-success">
							<p>
								There are
								<c:out value="${votingPoll.validVotes}" />
								valid votes.
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-danger">
							<p>
								There are
								<c:out value="${votingPoll.invalidVotes}" />
								invalid votes.
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>