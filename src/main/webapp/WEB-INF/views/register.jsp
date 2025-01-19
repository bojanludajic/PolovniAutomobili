<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
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

	<form action="/Polovni/auth/saveUser" method="post"
		modelAttribute="user">
		<h1>Registracija</h1>
		Ime: <input type="text" name="name" placeholder="Unesite vaše ime"
			required></input><br> Email: <input type="text" name="email"
			placeholder="Unesite vaš email" required></input><br> Korisnicko
		Ime: <input type="text" name="username"
			placeholder="Unesite korisnicko ime" required></input><br>
		Lozinka: <input type="password" name="password"
			placeholder="Unesite lozinku" required></input><br>
		<button type="submit">Registruj se</button>
	</form>
</body>
</html>