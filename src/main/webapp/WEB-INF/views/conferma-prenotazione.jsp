<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Esito Prenotazione</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>${messaggio}</h2>
    
    <c:if test="${not empty volo}">
    	<p>Codice Prenotazione: <strong>${codicePrenotazione}</strong></p>
	    <p>Da: <strong>${volo.cittaPartenza}</strong></p>
	    <p>A: <strong>${volo.cittaArrivo}</strong></p>
	    <p>Data e ora: <strong>${volo.oraPartenza}</strong></p>
	    <p>Peso del bagaglio: <strong>${pesoBagaglio} kg</strong></p>
	</c:if>
    
    <a href="<c:url value='/cerca'/>"><button>Prenota un altro volo</button></a>
</body>
</html>
