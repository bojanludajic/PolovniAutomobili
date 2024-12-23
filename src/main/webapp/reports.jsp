<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Izveštaji</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/reports.css'/>">
</head>
<body>
	<div class="top-bar">
		<a href="/Polovni/">Pocetna</a>
		<a href="/Polovni/admin/listingManagement">Upravljanje oglasima</a> <a
			href="/Polovni/admin/allMessages">Upravljanje porukama</a> <a
			href="${pageContext.request.contextPath}/logout">Log out</a>
	</div>

	<div class="container">
        <h1>Izveštaji</h1>
        <div class="form-container">
            <form action="/Polovni/admin/getListingReport" method="GET">
                <label for="userSelect">Odaberite korisnika:</label>
                <select name="idUser" id="userSelect" required>
                    <c:forEach items="${users}" var="u">
                        <option value="${u.idUser}">${u.name}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn">Oglasi korisnika</button>
            </form>
        </div>
    </div>
</body>
</html>
