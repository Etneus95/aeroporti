<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Elenco Voli Futuri</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<a href="<c:url value='/admin/gestione'/>"><button class="button">Indietro</button></a>

    <h2>Elenco dei voli futuri</h2>

    <table border="1">
        <thead>
            <tr>
                <th>ID Volo</th>
                <th>Partenza</th>
                <th>Data/Ora Partenza</th>
                <th>Destinazione</th>
                <th>Data/Ora Arrivo</th>
                <th>Posti Prenotati</th>
                <th>Peso Totale Bagagli (kg)</th>
                <th>ID Aeromobile</th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var="volo" items="${voli}">
                <tr>
                    <td>${volo.idVolo}</td>
                    <td>${volo.cittaPartenza}</td>
                    <td>${volo.oraPartenza}</td>
                    <td>${volo.cittaArrivo}</td>
                    <td>${volo.oraArrivo}</td>
                    <td>${volo.passeggeri}</td>
                    <td>${volo.merci}</td>
                    <td>${volo.tipoAereo.tipoAereo}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>
    <a href="<c:url value='/admin/gestione'/>"><button>Torna alla gestione</button></a>
</body>
</html>
