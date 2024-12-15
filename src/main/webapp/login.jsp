<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
	<h1>Ulogujte se</h1>
	<form method="post" action="${pageContext.request.contextPath}/login">
		<label for="username">Korisnicko ime:</label> <input type="text"
			id="username" name="username" required> <br> <label
			for="password">Lozinka:</label> <input type="password" id="password"
			name="password" required> <br>
			<button type="submit">Log in</button>
	</form>
	<a href="/Polovni/register.jsp">Register</a>
	<c:if test="${!empty message }">
		<p>${message }</p>
	</c:if>
</body>
</html>
