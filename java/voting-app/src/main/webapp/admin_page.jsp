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
<title>Admin Page</title>
</head>

<body>
	<div class="container-fluid">
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
					<!-- alert -->
				</c:if>
				<!-- Closing check on erros -->
			</div>
			<!-- col-md-4 -->
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Candidates</div>
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Candidate</th>
									<th>Political Party</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="candidate" items="${candidateList}">
									<tr>
										<td><c:out value="${candidate.name}" /></td>
										<td><c:out value="${candidate.politicalParty.name}" /></td>
										<td>
											<form class="form-inline" action="deleteCandidate"
												method="post">
												<input type="hidden" name="c_id"
													value="<c:out value='${candidate.id}'/>">
												<button type="submit" class="btn btn-danger">Delete</button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- panel-body -->

					<div class="panel-footer">
						<c:choose>
							<c:when test="${!politicalPartyList.isEmpty()}">
								<form class="form-inline" action="addCandidate" method="post">
									<div class="form-group">
										<label class="sr-only" for="c_name">Candidate Name</label> <input
											type="text" class="form-control" name="c_name"
											placeholder="Candidate Name">
									</div>
									<div class="form-group">
										<label for="political_party">Select Political Party:</label> <select
											class="form-control" name="political_party">
											<c:forEach var="politicalParty" items="${politicalPartyList}">
												<option value="<c:out value='${politicalParty.id}'/>" >
												<c:out value="${politicalParty.name}" />
												</option>
											</c:forEach>
										</select>
									</div>
									<button type="submit" class="btn btn-primary">Add
										Candidate</button>
								</form>
							</c:when>
							<c:otherwise>
								<p>Add a Political Party to add Candidates</p>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- panel-footer -->
				</div>
				<!-- panel -->
			</div>
			<!-- col-md-6 -->
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Political Parties</div>
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="politicalParty" items="${politicalPartyList}">
									<tr>
										<td><c:out value="${politicalParty.name}" /></td>
										<td>
											<button data-toggle="modal"
												data-target="#<c:out value='${politicalParty.id}'/>Modal"
												class="btn btn-danger">Delete</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- panel-body -->

					<div class="panel-footer">
						<form class="form-inline" action="addPoliticalParty" method="post">
							<div class="form-group">
								<label class="sr-only" for="p_name">Political Party Name</label>
								<input type="text" class="form-control" name="p_name"
									placeholder="Political Party Name">
							</div>
							<button type="submit" class="btn btn-primary">Add
								Political Party</button>
						</form>
					</div>
					<!-- panel-footer -->
				</div>
				<!-- panel -->
			</div>
			<!-- col-md-6 -->
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-md-4 col-md-offset-4 text-center">
				<form action="seedData" method="post">
					<button type="submit" class="btn btn-info">Seed With Data</button>
				</form>
			</div>
		</div>
	</div>
	<c:forEach var="politicalParty" items="${politicalPartyList}">
		<div class="modal fade"
			id="<c:out value='${politicalParty.id}'/>Modal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Modal Header</h4>
					</div>
					<form action="deletePoliticalParty" method="post">
						<div class="modal-body">
							<p>Deleting this will also delete candidates of this party.</p>
							<input type="hidden" name="p_id"
								value="<c:out value='${politicalParty.id}'/>">
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-danger">Delete</button>
							<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</c:forEach>
</body>

</html>