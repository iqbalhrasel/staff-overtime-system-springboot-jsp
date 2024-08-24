<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert OT here</title>
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="/webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
	
</head>
<body>
	<div> <%@ include file="../common/navbarAdmin.jspf" %> </div>
	<div class="container">
		<h3 class="mt-5 text-success"> <b> Overtime Entry System</b></h3>
		<hr>
		<form:form method="post" modelAttribute="ot" class="mt-3 form-control">
			<form:input type="hidden" path="id"/>
			<form:input type="hidden" path="name"/>
			<p>Name: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<form:select path="username" >
				<c:forEach items="${staffs}" var="staff">
					<form:option value="${staff.username}">${staff.name} - (${staff.username})</form:option>
				</c:forEach>
				</form:select>
			</p>
			
			<form:input type="hidden" path="role"/>
			Overtime date &nbsp; &nbsp; &nbsp;
			<form:input type="text" path="otDate" cssClass="datepick" htmlEscape="false" placeholder="Enter date"/>
			
			<form:input type="hidden" path="startTime"/>
			<br><br>
			Exit time &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			<form:input type="time" path="endTime" />
			
			<form:input type="hidden" path="otTime"/>
			<br><br>
			<p>OT Type &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<form:select path="otType">
					<form:option value="General">General</form:option>
					<form:option value="Ramadan">Ramadan</form:option>
					<form:option value="Non-Office Day">Non-Office Day</form:option>
				</form:select>			
			</p>
			<input type="submit" value="Save" class="btn btn-success mt-3">
		</form:form>
		<c:if test="${not empty message}">
			<div class="alert alert-success" role="alert">
			  ${message}
			</div>
		</c:if>
		
	</div>
	
	
	<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript">
	    $('.datepick').datepicker({
	        format: 'dd/mm/yyyy'
	    });
    </script>
</body>
</html>