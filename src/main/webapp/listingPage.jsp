<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Polovni ${listing.make } ${listing.model }</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/listing.css'/>">
</head>
<body>
	<div class="top-bar">
			<a href="/Polovni/">Pocetna</a>
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

	<div class="form-container">
		<c:if test="${empty listing.base64Image}">
		</c:if>
		<div class="image-container">
			<img src="data:image/jpeg;base64,${listing.base64Image }"
				alt="Listing Image">
		</div>

		<h1>${listing.name}</h1>
		<p>
			<strong>Marka:</strong> ${listing.make}
		</p>
		<p>
			<strong>Model:</strong> ${listing.model}
		</p>
		<p>
			<strong>Kubikaza:</strong> ${listing.engineSize} ccm
		</p>
		<p>
			<strong>Snaga motora:</strong> ${listing.horsepower} ks
		</p>
		<p>
			<strong>Kilometraza:</strong> ${listing.mileage} km
		</p>
		<p>
			<strong>Godiste:</strong> ${listing.year}
		</p>
		<p>
			<strong>Cena:</strong> ${listing.price}€
		</p>

		<sec:authorize access="isAuthenticated">
			<div class="button-container">
				<form action="/Polovni/user/favListing" method="post"
					style="display: inline;">
					<input type="hidden" name="idListing" value="${listing.idListing}">
					<button type="submit" class="heart-button" title="Like">&#x2764;</button>
				</form>

				<c:if test="${listing.idUser != idUser}">
					<form action="/Polovni/user/newMessage" method="get"
						style="display: inline;">
						<input type="hidden" name="idListing" value="${listing.idListing}">
						<button type="submit" class="message-button" title="Message">&#x1F4AC;</button>
					</form>
				</c:if>
			</div>
		</sec:authorize>
	</div>
</body>
</html>
