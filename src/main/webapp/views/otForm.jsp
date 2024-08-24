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
</head>
<body>
	<div> <%@ include file="common/navbar.jspf" %> </div>
	<div class="container">
		<h3 class="mt-5 text-success"> <b> Overtime Entry System</b></h3>
		<hr>
		<form:form method="post" modelAttribute="ot" class="mt-3" action="ot-entry">
			<form:input type="hidden" path="id"/>
			<form:input type="hidden" path="name"/>
			<p>Name: 
				<form:select path="username" >
				<c:forEach items="${staffs}" var="staff">
					<form:option value="${staff.username}">${staff.name} - (${staff.username})</form:option>
				</c:forEach>
				</form:select>
			</p>
			<br>
			<form:input type="hidden" path="role"/>
			<form:input type="hidden" path="otDate"/>
			<form:input type="hidden" path="startTime"/>
			<form:input type="hidden" path="endTime"/>
			<form:input type="hidden" path="otTime"/>
			<p>OT Type: <form:input type="text" readonly="true" path="otType" class="text-muted"/></p>
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
</body>
</html>