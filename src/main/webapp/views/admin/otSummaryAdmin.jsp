<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Overtime summary</title>
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="/webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
	
</head>
<body>
	<div> <%@ include file="../common/navbarAdmin.jspf" %> </div>
	
	<div class="container">
		<h4 class="text-light bg-secondary mt-3" id="csvName"><b>&nbsp; Staff Overtime Summary (${dateRange})</b></h4>
		<div class="d-flex justify-content-between align-items-center">
			<form method="post" class="mb-3">
				Start date
				<input type="text" name="startDate" class="datepicker">
				End date
				<input type="text" name="endDate" class="datepicker">
				<input type="submit" value="SEARCH" class="btn btn-success">
			</form>
			
			<button type="button" onclick="tableToCSV()" class="btn btn-outline-success mb-1">
	            DOWNLOAD EXCEL
	        </button>
        </div>
		<table class="table">
			<thead class="table-primary">
				<tr>
					<th>Staff name</th>
					<th>Designation</th>
					<th>Username</th>
					<th>Total Overtime</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${staffs}" var="staff">
					<tr>
						<td>${staff.name}</td>
						<td>${staff.role}</td>
						<td>${staff.username}</td>
						<td class="text-primary">${staff.finalOt} Hours</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
	</div>
	<script src="/resources/csvDownloader.js"></script>
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