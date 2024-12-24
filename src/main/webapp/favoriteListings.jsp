<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sacuvani oglasi</title>
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
			href="/Polovni/user/myListings">Moji oglasi</a> <a
			href="/Polovni/user/newListing">Postavi oglas</a> <a
			href="${pageContext.request.contextPath}/logout">Log out</a>
	</div>

	<div class="container">
		<h1>Sacuvani oglasi</h1>

		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>

		<c:forEach items="${favorites}" var="listing">
			<div class="listing-container">
					<div class="listing">
						<a href="/Polovni/listing/?id=${listing.idListing}" class="car-link">
	                		<img src="data:image/jpg;base64,${listing.base64Image}"
	                    		alt="${listing.name}" class="listing-image">
	           			</a>
	           			
						<div class="listing-info">
							<a href="/Polovni/listing/?id=${listing.idListing}" class="car-link">
								<h2>${listing.name}</h2>
								<p class="price">${listing.price}€</p>
								<div class="listing-details">
									<p>${listing.make}-${listing.model}(${listing.year})</p>
									<p>Motor: ${listing.engineSize}ccm, ${listing.horsepower} ks</p>
									<p>Kilometraza: ${listing.mileage} km</p>
								</div>
							</a>
						</div>
						<div class="trash-icon">
							<a href="/Polovni/user/deleteFavorite?idListing=${listing.idListing}"
								class="trash-link"> <i class="fa-solid fa-minus"></i>
							</a>
						</div>
					</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>
