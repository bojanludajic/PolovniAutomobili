<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" href="<c:url value='/styles/auth.css'/>">
<link rel="icon" href="<c:url value='/icon/favicon.ico'/>"
	type="image/x-icon">


</head>
<body>

	<div class="top-bar">
		<a href="${pageContext.request.contextPath}/">Pocetna</a>
	</div>

	<div class="login-container">
		<h1>Prijava</h1>
		<form method="post" action="${pageContext.request.contextPath}/login">
			<label for="username">Korisnicko ime:</label> <input type="text"
				id="username" name="username" required> <br> <label
				for="password">Lozinka:</label> <input type="password" id="password"
				name="password" required> <br>
			<button type="submit">Log in</button>
		</form>
		Nemas nalog? <a href="/Polovni/register.jsp">Registruj se!</a>
		<c:if test="${!empty message}">
			<p class="c-message">${message}</p>
		</c:if>
	</div>
</body>
</html>
