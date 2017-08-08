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
<title>Voting Page</title>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4 text-center">
				<c:if test="${errors != null}">
					<div class="alert alert-danger">
						<ul>
							<c:forEach var="error" items="${errors}">
								<li class=""><c:out value="${error}" /></li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<c:if test="${success != null}">
					<div class="alert alert-success">
						<p>
							<c:out value="${success}" />
						</p>
					</div>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-md-offset-2 text-center">
				<h2>Please rank candidates in order from 1 downards.</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-default">
					<div class="panel-heading">Voting Poll</div>
					<form class="form-inline" action="vote" method="post">
						<div class="panel-body">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Candidate</th>
										<th>Political Party</th>
										<th>Rank</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="candidate" items="${candidateList}">
										<tr>
											<td><c:out value="${candidate.name}" /></td>
											<td><c:out value="${candidate.politicalParty.name}" />
											</td>
											<td><input type="number"
												name="<c:out value='${candidate.id}'/>" min="1"
												max="${candidateList.size()}"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="panel-footer">
							<c:if test="${!candidateList.isEmpty()}">
								<button type="submit" class="btn btn-primary">Submit
									Vote</button>
							</c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>