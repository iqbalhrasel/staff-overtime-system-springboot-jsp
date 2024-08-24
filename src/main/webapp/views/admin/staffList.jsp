<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Staff List</title>
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
</head>
<body>
	<div> <%@ include file="../common/navbarAdmin.jspf" %> </div>
	
	<div class="container">
		<h3 class="text-primary mt-3"><b>Staff List</b></h3>
		<table class="table">
			<thead class="table-primary">
				<tr>
					<th>Staff name</th>
					<th>Staff designation</th>
					<th>Staff username</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${staffs}" var="staff">
					<tr>
						<td>${staff.name}</td>
						<td>${staff.role}</td>
						<td>${staff.username}</td>
						<td> <a href="update-staff?id=${staff.id}" class="btn btn-success">Update</a> 
							<a href="delete-staff?id=${staff.id}" class="btn btn-warning">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>