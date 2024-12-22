<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moji Oglasi</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/personalListings.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

	<div class="top-bar">
		<a href="${pageContext.request.contextPath}/">Pocetna</a> <a
			href="/Polovni/user/newListing">Postavi oglas</a> <a
			href="/Polovni/user/favListings">Sacuvani oglasi</a> <a
			href="${pageContext.request.contextPath}/logout">Log out</a>
	</div>

	<div class="container">
		<h1>Moji oglasi</h1>

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
								<p>${listing.make}- ${listing.model} (${listing.year})</p>
								<p>Motor: ${listing.engineSize}ccm, ${listing.horsepower} ks</p>
								<p>Kilometraza: ${listing.mileage} km</p>
							</div>
						</div>
						<div class="trash-icon">
							<a href="/Polovni/user/deleteListing?id=${listing.idListing}"
								class="trash-link"> <i class="fas fa-trash-alt"></i>
							</a>
						</div>
					</div>	
			</div>
		</c:forEach>
	</div>

</body>
</html>
