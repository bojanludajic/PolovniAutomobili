<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upravljanje porukama</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/index.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/allMessages.css'/>">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

    <div class="top-bar">
		<a href="${pageContext.request.contextPath}/">Pocetna</a> <a
			href="/Polovni/admin/listingManagement">Upravljanje oglasima</a> <a
			href="/Polovni/admin/reports">Izvestaji</a> <a
			href="${pageContext.request.contextPath}/logout">Log out</a>
	</div>

    <div class="container">
        <h1>Sve poruke</h1>

        <c:if test="${!empty messages}">
            <c:forEach items="${messages}" var="m">
                <div class="message">
                    <div class="message-content">
                        <p><strong>Od:</strong> ${m.sender.name } | <strong>Za:</strong> ${m.receiver.name }</p>
                        <p><strong>Tekst:</strong> ${m.content} | <strong>Vreme:</strong> ${m.timestamp }</p>
                    </div>
                    <div class="delete-icon">
                        <a href="/Polovni/admin/deleteMessage?idMessage=${m.idMessage}" title="Obrisi poruku">
                            <i class="fas fa-trash-alt"></i>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${empty messages}">
            <p style="text-align: center; color: #7f8c8d;">No messages to display.</p>
        </c:if>
    </div>

</body>
</html>
