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
	href="<c:url value='/styles/search.css'/>">
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
			<a href="/Polovni/login.jsp">Log in</a>
		</sec:authorize>
	</div>

	<div class="form-container">
		<h1>Pretraga oglasa</h1>

		<form action="/Polovni/search/" method="get" id="makeForm">
			<div style="margin-bottom: 10px;">
				<label for="marka">Marka:</label> <select name="make" id="marka"
					required onchange="this.form.submit()">
					<option value="">Izaberite marku</option>
					<c:forEach items="${makes}" var="m">
						<option value="${m}"
							${m == selectedMake ? 'selected="selected"' : ''}>${m}</option>
					</c:forEach>
				</select>
			</div>
		</form>

		<form action="/Polovni/search/submit" method="get">
			<input type="hidden" name="make" value="${selectedMake}">

			<div class="form-group">
				<label for="model">Model:</label> <select id="model" name="model">
					<option value="">Bilo koji</option>
					<c:forEach items="${models}" var="model">
						<option value="${model}">${model}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label for="price">Cena:</label> <input type="number" id="price-min"
					name="priceMin" placeholder="Minimalna cena" min="0"> <input
					type="number" id="price-max" name="priceMax" placeholder="Maksimalna cena"
					min="0">
			</div>

			<div class="form-group">
				<label for="year">Godiste:</label> <input type="number"
					id="year-min" name="yearMin" placeholder="Minimalno godiste" step="1" min="1900">
				<input type="number" id="year-max" name="yearMax" placeholder="Maksimalo godiste"
					step="1" min="1900">
			</div>

			<div class="form-group">
				<label for="year">Kubikaza:</label> <input type="number"
					id="size-min" name="sizeMin" placeholder="Minimalna kubikaza" step="1" min="1">
				<input type="number" id="size-max" name="sizeMax" placeholder="Maksimalna kubikaza"
					step="1" min="1">
			</div>

			<div class="form-group">
				<label for="year">Snaga motora (ks):</label> <input type="number"
					id="size-min" name="powerMin" placeholder="Minimalna snaga" step="1" min="1">
				<input type="number" id="size-max" name="powerMax" placeholder="Maksimalna snaga"
					step="1" min="1">
			</div>

			<button type="submit">Pretrazi</button>
		</form>
	</div>
</body>
</html>
