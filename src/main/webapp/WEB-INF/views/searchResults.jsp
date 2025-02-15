<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pretraga</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/personalListings.css'/>">
<link rel="icon" href="<c:url value='/icon/favicon.ico'/>"
	type="image/x-icon">
</head>
<body>
	<div class="top-bar">
		<a href="/Polovni/">Pocetna</a>
		<sec:authorize access="isAuthenticated">
			<sec:authorize access="hasRole('User')">
				<a href="/Polovni/user/myListings">Moji oglasi</a>
				<a href="/Polovni/user/newListing">Postavi oglas</a>
				<a href="/Polovni/user/favListings">Sacuvani oglasi</a>
				<a href="${pageContext.request.contextPath}/logout">Log out</a>
			</sec:authorize>
			<sec:authorize access="hasRole('Admin')">
				<a href="/Polovni/admin/listingManagement">Upravljanje oglasima</a>
				<a href="/Polovni/admin/allMessages">Upravljanje porukama</a>
				<a href="/Polovni/admin/reports">Izvestaji</a>
				<a href="${pageContext.request.contextPath}/logout">Log out</a>
			</sec:authorize>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated">
			<a href="/Polovni/auth/login">Log in</a>
		</sec:authorize>
	</div>


	<div class="container">
		<h1>Rezultati pretrage</h1>

		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>

		<c:forEach items="${listings}" var="listing">
			<div class="listing-container">
				<a href="/Polovni/listing/?id=${listing.idListing }"
					class="car-link">
					<div class="listing">
						<img src="data:image/jpg;base64,${listing.base64Image}"
							alt="${listing.name}" class="listing-image">
						<div class="listing-info">
							<h2>${listing.name}</h2>
							<p class="price">${listing.price}€</p>
							<div class="listing-details">
								<p>${listing.make}-${listing.model} (${listing.year})</p>
								<p>Motor: ${listing.engineSize}ccm, ${listing.horsepower} ks</p>
								<p>Kilometraza: ${listing.mileage} km</p>
							</div>
						</div>
					</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>