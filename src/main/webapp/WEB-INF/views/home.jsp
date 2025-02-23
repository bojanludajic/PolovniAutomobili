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
	<link rel="icon" href="<c:url value='/icon/favicon.ico'/>" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/index.css'/>">
	</head>
	<body>
	
		<div class="top-bar">
			<a href="/Polovni/search/">Pretraga</a>
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
	
		<div class="car-container">
    <c:forEach items="${listings}" var="listing">
        <a href="/Polovni/listing/?id=${listing.idListing }" class="car-link">
            <div class="car">
                <div class="price-tag">
                    ${listing.price} €
                </div>

               <img src="data:image/jpeg;base64,${listing.base64Image}" alt="${listing.name}" class="car-image">


                <div class="car-details">
                    <p>${listing.make} - ${listing.model}</p>
                    <p>${listing.year }</p>
                </div>
            </div>
        </a>
    </c:forEach>
</div>
    <c:if test="${!empty user }">
    	<h1>${user.name }</h1>
    </c:if>

	</body>
	</html>
