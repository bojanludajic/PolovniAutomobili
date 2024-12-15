<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Polovni automobili</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/styles/index.css"/>">
</head>
<body>

	<div class="top-bar">
		<a href="">Pretraga</a>
		<sec:authorize access="isAuthenticated">
			<a href="/Polovni/personalListings.jsp">Moji oglasi</a>
			<a href="/Polovni/listings/">Postavi oglas</a>
			<a href="${pageContext.request.contextPath}/logout">Log out</a>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated">
			<a href="/Polovni/login.jsp">Log in</a>
		</sec:authorize>

	</div>
</body>
</html>