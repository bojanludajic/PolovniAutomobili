<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delovi za ${make} ${model}</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/parts.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon" href="<c:url value='/icon/favicon.ico'/>"
	type="image/x-icon">
</head>
<body>

	<div class="top-bar">
		<a href="/Polovni/">Pocenta</a>
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
	</div>

	<div class="container">
		<c:if test="${!empty message}">
			<div class="message info">${message}</div>
		</c:if>
		<h1>Delovi za ${make} ${model}</h1>

		<c:if test="${!empty parts}">
			<c:forEach items="${parts}" var="p">
				<div class="part">
					<div class="part-content">
						<p>
							<strong>Deo:</strong> ${p.name} | <strong>Dostupnost:</strong>
							${p.availability}
						</p>
					</div>
					<c:if test="${p.availability > 0 }">
						<form action="/Polovni/parts/order" method="get" class="buy-form">
							<input type="hidden" name="make" value="${make}"> <input
								type="hidden" name="model" value="${model}"> <input
								type="hidden" name="name" value="${p.name}">
							<button type="submit" title="Kupi">
								<i class="fas fa-cart-plus"></i>
							</button>
						</form>
					</c:if>
				</div>
			</c:forEach>
		</c:if>

		<c:if test="${empty parts}">
			<p style="text-align: center; color: #7f8c8d;">No parts available
				for this car model.</p>
		</c:if>
	</div>

</body>
</html>
