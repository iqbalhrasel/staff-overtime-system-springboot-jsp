<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create New Staff ID</title>
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
</head>
<body>
	<div> <%@ include file="../common/navbarAdmin.jspf" %> </div>
	<div class="container">
		<h3 class="text-primary mt-3"><b>Create New Staff ID</b></h3>
		
		<form:form method="post" modelAttribute="staff" cssClass="form-control">
			<form:input path="id" type="hidden"/>
			<br>
			
			<p>Staff name &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<form:input path="name" type="text"/>
				<form:errors path="name"/>
			</p>
			
			<p>Staff designation &nbsp; &nbsp;
				<form:select path="role">
					<c:forEach items="${designations}" var="designation">
						<form:option value="${designation.role}">${designation.role}</form:option>
					</c:forEach>
					
					
				</form:select>
			</p>
			
			<p>Staff username &nbsp; &nbsp; &nbsp; 
				<c:choose>
					<c:when test="${readOnlySettings}">
						<form:input path="username" type="text" readonly="true" cssClass="text-muted"/>
					</c:when>
					<c:otherwise>
						<form:input path="username" type="text" />
						<form:errors path="username"/>
					</c:otherwise>
				</c:choose>				
			</p>
			
			<form:input path="finalOt" type="hidden"/>
			
			<input type="submit" value="Save" class="btn btn-primary">
		</form:form>
		<br>
		<p class="text-success">${msg}</p>
	</div>
	<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
</body>
</html>