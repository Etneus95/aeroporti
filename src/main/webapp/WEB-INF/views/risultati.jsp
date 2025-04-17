<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<title>Risultati Voli</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:choose>
    <c:when test="${not empty risultati}">
        <h2>Voli disponibili per:</h2>
        <h2>${cittaPartenza} - ${cittaArrivo}</h2>
        <c:forEach var="volo" items="${risultati}">
            <div>
                <p><strong>${volo.oraPartenza}</strong></p>
                
                <form action="<c:url value='/confermaPrenotazione'/>" method="post">
		            <input type="hidden" name="idVolo" value="${volo.idVolo}" />
		            <input type="hidden" name="pesoBagaglio" value="${pesoBagaglio}" />
		            <button type="submit">Prenota</button>
		        </form>
            </div>
            <hr/><br>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h2>Nessuno volo trovato per:</h2>
        <h3>${cittaPartenza} - ${cittaArrivo}</h3>
    </c:otherwise>
</c:choose>
	<p><a href="<c:url value='/cerca'/>">Torna alla ricerca</a></p>
</body>
</html>
