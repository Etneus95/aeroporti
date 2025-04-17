<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Conferma prenotazione</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<h2>Conferma Prenotazione</h2>
	<p>Da: <strong>${volo.cittaPartenza}</strong></p>
    <p>A: <strong>${volo.cittaArrivo}</strong></p>
    <p>Data e ora: <strong>${volo.oraPartenza}</strong></p>
    <p>Peso del bagaglio: <strong>${pesoBagaglio} kg</strong></p>
	<form action="<c:url value='/prenota'/>" method="post">
	    <input type="hidden" name="idVolo" value="${volo.idVolo}" />
	    <input type="hidden" name="pesoBagaglio" value="${pesoBagaglio}" />
	    <button type="submit">Conferma</button>
	</form>
</body>
</html>