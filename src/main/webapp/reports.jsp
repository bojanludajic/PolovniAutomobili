<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Izveštaji</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/index.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/reports.css'/>">
</head>
<body>
    <div class="top-bar">
        <a href="/Polovni/admin/listingManagement">Upravljanje oglasima</a>			
		<a href="/Polovni/admin/allMessages">Upravljanje porukama</a>
		<a href="${pageContext.request.contextPath}/logout">Log out</a>
    </div>

    <div class="container">
        <h1>Izveštaji</h1>
        <div class="button-container">
            <a href="${pageContext.request.contextPath}/report/sales" class="report-button">Izveštaj o prodaji</a>
            <a href="${pageContext.request.contextPath}/report/users" class="report-button">Izveštaj o korisnicima</a>
            <a href="${pageContext.request.contextPath}/report/vehicles" class="report-button">Izveštaj o vozilima</a>
        </div>
    </div>
</body>
</html>
