<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ot log</title>
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="/webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
</head>
<body>
	<div> <%@ include file="common/navbar.jspf" %> </div>
	
	<div class="container">
		<h4 class="text-light bg-secondary mt-3"><b>&nbsp; Staff Overtime Log (${daterange})</b></h4>
		<c:if test="${not empty isMessage}">
			<c:choose>
				<c:when test="${message}">
					<div class="alert alert-success" role="alert">
					  Updated successfully.
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-warning" role="alert">
						Failed to update. Overtime date is backdated.
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		<form method="post" class="mb-3">
			<b>Name</b>
			<select name="username">
				<option value="ALL">ALL</option>
				<c:forEach items="${staffs}" var="staff">
					<option value="${staff.username}">${staff.name} (${staff.username})</option>
				</c:forEach>
			</select>
			<b>Date</b>
			<input type="text" name="startDate" class="datepicker">
			To
			<input type="text" name="endDate" class="datepicker">
			<input type="submit" value="SEARCH" class="btn btn-primary">
		</form>
		<table class="table">
			<thead class="table-primary">
				<tr>
					<th>Staff name</th>
					<th>Designation</th>
					<th>Username</th>
					<th>Date</th>
					<th>Start time</th>
					<th>Exit time</th>
					<th>Overtime</th>
					<th>Ot type</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ots}" var="ot">
					<tr>
						<td>${ot.name}</td>
						<td>${ot.role}</td>
						<td>${ot.username}</td>
						<td>${ot.otDate}</td>
						<td>${ot.startTime}</td>
						<td>${ot.endTime}</td>
						<td class="text-primary">${ot.otTime} Hours</td>
						<td>${ot.otType}</td>
						<td> <a href="update-ot?id=${ot.id}" class="btn btn-success">Update</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript">
	    $('.datepicker').datepicker({
	        format: 'dd/mm/yyyy'
	    });
    </script>
</body>
</html>