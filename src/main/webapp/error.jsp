<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doslo je do greske</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/index.css'/>">
    
</head>
<body>

    <div class="top-bar">
        <a href="/Polovni/" style="color: white; text-decoration: none; font-size: 16px;">Pocetna</a>
    </div>

    <div style="display: flex; justify-content: center; align-items: center; height: 80vh; font-size: 24px; color: #333;">
        <p>Doslo je do greske</p>
    </div>
    <c:if test="${!empty rateLimitExceeded }"> 
        <br><p>Prekoracili ste broj zahteva! Molim sacekajte.</p>
    </c:if>

</body>
</html>
