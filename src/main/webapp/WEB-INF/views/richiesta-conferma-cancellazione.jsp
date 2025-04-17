<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Conferma cancellazione</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<h2>Conferma Cancellazione</h2>
	
	<p><strong>Cancellare il seguente volo?</strong></p>
        
	<p>ID: <strong>${volo.idVolo}</strong></p>
	<p>Da: <strong>${volo.cittaPartenza}</strong></p>
    <p>A: <strong>${volo.cittaArrivo}</strong></p>
    <p>Partenza: <strong>${volo.oraPartenza}</strong></p>
    <p>Arrivo: <strong>${volo.oraArrivo}</strong></p>
    <p>Passeggeri: <strong>${volo.passeggeri}</strong></p>
    <p>Peso bagagli: <strong>${volo.merci} kg</strong></p>
    <p>Data e ora: <strong>${volo.tipoAereo.tipoAereo}</strong></p>
    
	<form action="<c:url value='/admin/cancella-volo'/>" method="post">
	    <input type="hidden" name="idVolo" value="${volo.idVolo}" />
	    <button type="submit">Cancella</button>
	</form>
	
	<a href="<c:url value='/admin/gestione'/>"><button class="button">Annulla</button></a>
</body>
</html>