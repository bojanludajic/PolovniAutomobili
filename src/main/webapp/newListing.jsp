<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novi oglas</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/newListing.css'/>">
</head>
<body>

	<div class="top-bar">
		<a href="${pageContext.request.contextPath}/">Pocetna</a>
	</div>

	<div class="form-container">
		<h1>Novi oglas</h1>
		<form action="/Polovni/user/cars" method="get" id="makeForm">
    <div style="margin-bottom: 10px;">
        <label for="marka">Marka:</label>
        <select name="make" id="marka" required onchange="this.form.submit()">
            <option value="">Izaberite marku</option>
            <c:forEach items="${makes}" var="m">
                <option value="${m}" ${m == selectedMake ? 'selected="selected"' : ''}>${m}</option>
            </c:forEach>
        </select>
    </div>
</form>

<form action="/Polovni/user/saveListing" method="post", modelAttribute="listing">
    <div style="margin-bottom: 10px;">
        <input type="hidden" name="make" value="${selectedMake}">
    
        <label for="model">Model:</label>
        <select name="model" id="model" required>
            <option value="">Izaberite model</option>
            <c:forEach items="${models}" var="mo">
                <option value="${mo}">${mo}</option>
            </c:forEach>
        </select>
    </div>

    <div style="margin-bottom: 10px;">
        <label for="ime">Naslov:</label>
        <input type="text" name="name" id="ime" required>
    </div>

    <div style="margin-bottom: 10px;">
        <label for="cena">Cena:</label>
        <input type="number" name="price" id="cena" required>
    </div>

    <div style="margin-bottom: 10px;">
        <label for="kilometraza">Kilometraza:</label>
        <input type="number" name="mileage" id="kilometraza" required>
    </div>

    <div style="margin-bottom: 10px;">
        <label for="godiste">Godiste:</label>
        <input type="number" name="year" id="godiste" required>
    </div>

    <div style="margin-bottom: 10px;">
        <label for="kubikaza">Kubikaza:</label>
        <input type="number" name="engineSize" id="kubikaza" required>
    </div>

    <div style="margin-bottom: 10px;">
        <label for="snaga">Snaga motora (ks):</label>
        <input type="number" name="horsepower" id="snaga" required>
    </div>

    <div style="margin-bottom: 10px;">
        <label for="slika">Slika:</label>
        <input type="file" name="image" id="slika" accept=".png, .jpg, .jpeg" required>
    </div>

    <div>
        <button type="submit">Postavi oglas</button>
    </div>
</form>

	</div>

</body>
</html>
