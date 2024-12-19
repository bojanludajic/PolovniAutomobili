<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Polovni ${listing.make } ${listing.model }</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/listing.css'/>">
</head>
<body>
    <div class="top-bar">
        <a href="/Polovni/">Pocetna</a>
    </div>

    <div class="form-container">
        <c:if test="${not empty listing.base64Image}">
            <div class="image-container">
                <img src="data:image/jpeg;base64,${listing.base64Image }" alt="Listing Image">
            </div>
        </c:if>

        <h1>${listing.name}</h1>
        <p><strong>Marka:</strong> ${listing.make}</p>
        <p><strong>Model:</strong> ${listing.model}</p>
        <p><strong>Kubikaza:</strong> ${listing.engineSize} ccm</p>
        <p><strong>Snaga motora:</strong> ${listing.horsepower} ks</p>
        <p><strong>Kilometraza:</strong> ${listing.mileage} km</p>
        <p><strong>Godiste:</strong> ${listing.year}</p>
        <p><strong>Cena:</strong> ${listing.price}€</p>
    </div>
</body>
</html>
