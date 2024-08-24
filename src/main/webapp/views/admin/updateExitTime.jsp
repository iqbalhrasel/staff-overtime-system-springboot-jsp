<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Update office exit time</title>
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
	
</head>
<body>
	<div> <%@ include file="../common/navbarAdmin.jspf" %> </div>
	<div class="container">
		<h4 class="mt-3">Update office end time</h4>
		<form:form modelAttribute="officeExitTime" cssClass="form-control mt-3">
			<form:input path="id" type="hidden"/>
			General office end time &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			<form:input type="time" path="generalExitTime" />
			
			<br> <br>
			Ramadan office end time &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
			<form:input type="time" path="ramadanExitTime"  />

			<br> <br>
			Non office day office end time &nbsp; &nbsp; &nbsp;
			<form:input type="time" path="nonOfficeExitTime"/>
			
			<br> <br>
			<input type="submit" value="Update" class="btn btn-primary">
		</form:form>
		<p>${alert}</p>
	</div>
	
	
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    
</body>
</html>