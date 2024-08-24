<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
</head>
<body>
	<div> <%@ include file="../common/navbarAdmin.jspf" %> </div>
	<div class="container">
		<h3 class="mt-2">Welcome to admin panel</h3>
		
		
		<table class="table table-primary">
			<thead>
				<tr>
					<th>Particulars</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Create new overtime entry</td>
					<td><a href="create-ot" class="btn btn-primary">Create overtime</a></td>
				</tr>
				<tr>
					<td>Overtime Log</td>
					<td><a href="ot-log" class="btn btn-primary">Overtime log</a></td>
				</tr>
				<tr>
					<td>Overtime Summary</td>
					<td><a href="ot-summary" class="btn btn-primary">Overtime summary</a></td>
				</tr>
				<tr>
					<td>View staff info</td>
					<td><a href="staffs" class="btn btn-primary">View staff list</a></td>
				</tr>
				<tr>
					<td>Create new staff ID</td>
					<td><a href="create-staff-id" class="btn btn-primary">Create new staff</a></td>
				</tr>
				<tr>
					<td>Create / update designation</td>
					<td><a href="create-designation" class="btn btn-primary">Create designation</a></td>
				</tr>
				<tr>
					<td>Update overtime start time</td>
					<td><a href="update-time" class="btn btn-primary">Update time</a></td>
				</tr>
			</tbody>
		</table>
	</div>
	<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>