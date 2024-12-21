<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nova poruka</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/newMessage.css'/>">

</head>
<body>
	<div class="top-bar">
		<a href="/Polovni/search/">Pretraga</a>
		<sec:authorize access="isAuthenticated">
			<a href="/Polovni/user/myListings">Moji oglasi</a>
			<a href="/Polovni/user/newListing">Postavi oglas</a>
			<a href="/Polovni/user/favListings">Sacuvani oglasi</a>
			<a href="${pageContext.request.contextPath}/logout">Log out</a>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated">
			<a href="/Polovni/login.jsp">Log in</a>
		</sec:authorize>
	</div>


	<div class="container">
		<div class="listing-info">
			<h1>Prodavac: ${seller.name} (${seller.email})</h1>
			<p>
				<strong>Marka:</strong> ${listing.make}
			</p>
			<p>
				<strong>Model:</strong> ${listing.model}
			</p>
			<p>
				<strong>Cena:</strong> ${listing.price}€
			</p>
		</div>

		<form action="/Polovni/user/sendMessage" method="post"
			class="message-form">
			<input type="hidden" name="idSender" value="${user.idUser}" /> <input
				type="hidden" name="idReceiver" value="${seller.idUser}" /> <label
				for="message">Poruka:</label><br>
			<textarea id="message" name="text"
				placeholder="Unesite tekst poruke..."></textarea>
			<br>
			<br>

			<button type="submit">Posalji</button>
		</form>
	</div>
</body>
</html>
