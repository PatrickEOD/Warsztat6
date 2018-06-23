<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> <!-- powoduje że tagliby nie sa ingorowane -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<div>
	<h2>Login!</h2>
	<form method="post" action="/Warsztat6/login">
		<input type="text" name="username" placeholder="Enter username">
		<input type="password" name="password" placeholder="Enter password">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Log in">
	</form>
</div>
<div>
	<h2>Register!</h2>
	<h4>if you do not have login...</h4>
	<form:form method="post" action="/Warsztat6/register" modelAttribute="user">
		<form:input type="text" path="userName" placeholder="Enter username"/>
		<form:input type="password" path="password" placeholder="Enter password"/>
		<form:input type="text" path="email" placeholder="Enter email"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Register">
	</form:form>
</div>
</body>
</html>