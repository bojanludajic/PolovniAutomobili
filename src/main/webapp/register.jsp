<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<form action="/Polovni/user/saveUser" method="post"
		modelAttribute="user">
		Email: <input type="text" name="email"></input><br> Korisnicko
		Ime: <input type="text" name="username"></input><br> Lozinka: <input
			type="password" name="password"></input><br> Ime: <input
			type="text" name="name"></input><br>
		<button type="submit">Registruj se</button>
	</form>
</body>
</html>