<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moji Oglasi</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/personalListings.css'/>">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

    <div class="top-bar">
        <a href="/Polovni/">Pocetna</a>
        <a href="">Pretraga</a>
        <a href="/Polovni/user/newListing">Postavi oglas</a>
        <a href="${pageContext.request.contextPath}/logout">Log out</a>
    </div>

    <div class="container">
        <h1>Moji Oglasi</h1>
        
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>

        <c:forEach items="${listings}" var="listing">
            <div class="listing-container">
                <div class="listing">
                    <a href="/Polovni/listing/?id=${listing.idListing }" class="car-link">
                    <div class="listing-info">
                        <img src="data:image/jpg;base64,${listing.base64Image}" alt="${listing.name}" class="listing-image">
                        <div class="listing-details">
                            <h2>${listing.name}</h2>
                            <p>${listing.make} - ${listing.model} (${listing.year})</p>
                            <p>Motor: ${listing.engineSize}L, ${listing.horsepower} HP</p>
                            <p>Kilometraza: ${listing.mileage} km</p>
                            <p>Cena: ${listing.price} €</p>
                        </div>
                    </div>

                    <div class="trash-icon">
                        <a href="/Polovni/listing/deleteListing?id=${listing.idListing}" class="trash-link">
                            <i class="fas fa-trash-alt"></i>
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</body>
</html>
