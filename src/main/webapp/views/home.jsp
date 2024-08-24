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
	<div> <%@ include file="common/navbar.jspf" %> </div>
	<div class="container">
		<a href="ot-entry-gen" class="btn btn-primary mt-5">General OT Entry</a>
		<br>
		<a href="ot-entry-r" class="btn btn-primary mt-3">Ramadan OT Entry</a>
		<br>
		<a href="ot-entry-n" class="btn btn-primary mt-3">Non Office-day OT Entry</a>
	</div>
	<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>