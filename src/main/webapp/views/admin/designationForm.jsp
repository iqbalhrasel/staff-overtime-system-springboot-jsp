<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Create designation</title>
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
</head>
<body>
	<div> <%@ include file="../common/navbarAdmin.jspf" %> </div>
	<div class="container">
	
		<form:form modelAttribute="designation">
			<b>Designation</b>
			<form:input path="id" type="hidden"/>
			<form:input path="role" htmlEscape="false" placeholder="Enter designation to create"/>
			<input type="submit" value="Save" class="btn btn-success">
			${alert}
		</form:form>
		<br>
		<table class="table table-primary">
			<thead>
				<tr>
					<th>Sl.</th>
					<th>Designations</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${designations}" var="designation">
					<tr>
						<td>${designation.id}</td>
						<td>${designation.role}</td>
						<td> <a href="edit-designation?id=${designation.id}" class="btn btn-primary">Edit</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>