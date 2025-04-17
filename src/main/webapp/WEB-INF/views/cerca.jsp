<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Ricerca Voli</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>Cerca un volo</h2>
		<!-- DEBUG
		<c:forEach var="aeroporto" items="${aeroporti}">
		    <p>${aeroporto}</p> 
		    <p>${aeroporto.citta}</p> 
		</c:forEach>
		-->
    <form action="cerca" method="post">
        <label>Partenza:</label>
        <select name="cittaPartenza">
            <c:forEach var="aeroporto" items="${aeroporti}">
                <option value="${aeroporto.citta}">${aeroporto.citta} (${aeroporto.nazione})</option>
            </c:forEach>
        </select><br/>
        <label>Arrivo:</label>
        <select name="cittaArrivo">
            <c:forEach var="aeroporto" items="${aeroporti}">
                <option value="${aeroporto.citta}">${aeroporto.citta} (${aeroporto.nazione})</option>
            </c:forEach>
        </select><br/>

        <label>Data:</label>
        <input type="date" name="data" required/><br/>

        <label for="pesoBagaglio">Peso Bagaglio (kg):</label>
	    <input type="number" id="pesoBagaglio" name="pesoBagaglio" step="1" min="0" required />
	    
        <input type="submit" value="Cerca voli"/>
    </form>
</body>
</html>
