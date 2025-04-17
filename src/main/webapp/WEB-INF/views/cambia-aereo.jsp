<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Cambio Aereo</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Seleziona Aereo per il Volo</h1>

    <form action="<c:url value='/admin/salva-modifica-aereo'/>" method="post">
        <input type="hidden" name="idVolo" value="${volo.idVolo}"/>

        <label for="aereo">Aereo:</label>
        <select name="aereoId" id="aereo">
            <c:forEach var="aereo" items="${aereiDisponibili}">
                <!-- Qui l'option mostra il modello e capacità, ma invia solo l'ID -->
                <option value="${aereo.tipoAereo}">
                    ${aereo.tipoAereo}</option>
            </c:forEach>
        </select>

        <button type="submit">Salva Modifica</button>
    </form>
   <br> 
   
    <table border="1">
        <thead>
            <tr>
                <th>ID Aereo</th>
                <th>Capacità Passeggeri</th>
                <th>Capacità Merci</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="aereo" items="${aereiDisponibili}">
                <tr>
                    <td>${aereo.tipoAereo}</td>
                    <td>${aereo.numPass}</td>
                    <td>${aereo.qtaMerci}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>