<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Aggiungi Volo</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<a href="<c:url value='/admin/gestione'/>"><button class="button">Indietro</button></a>

    <h1>Aggiungi un Nuovo Volo</h1>
	
	<c:if test="${not empty errore}">
    <p>${errore}</p>
	</c:if>
	
    <form action="<c:url value='/admin/aggiungi-volo'/>" method="post">
        <div>
            <label for="idAeroportoPartenza">Aeroporto di Partenza:</label>
            <select id="idAeroportoPartenza" name="idAeroportoPartenza">
                <c:forEach var="aeroporto" items="${aeroporti}">
                    <option value="${aeroporto.idAeroporto}">${aeroporto.citta} (${aeroporto.nazione})</option>
                </c:forEach>
            </select>
        </div>
        
        <div>
            <label for="idAeroportoArrivo">Aeroporto di Arrivo:</label>
            <select id="idAeroportoArrivo" name="idAeroportoArrivo">
                <c:forEach var="aeroporto" items="${aeroporti}">
                    <option value="${aeroporto.idAeroporto}">${aeroporto.citta} (${aeroporto.nazione})</option>
                </c:forEach>
            </select>
        </div>
        
        <div>
            <label for="idAereo">Aereo:</label>
            <select id="idAereo" name="idAereo">
                <c:forEach var="aereo" items="${aerei}">
                    <option value="${aereo.tipoAereo}">${aereo.tipoAereo} (${aereo.numPass} pass. - ${aereo.qtaMerci} kg merci)</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="dataPartenza">Data e Ora di Partenza:</label>
            <input type="datetime-local" id="dataPartenza" name="dataPartenza" required>
        </div>

        <div>
            <label for="dataArrivo">Data e Ora di Arrivo:</label>
            <input type="datetime-local" id="dataArrivo" name="dataArrivo" required>
        </div>

        <!-- Campi di passeggeri e merci non visibili in quanto settati a zero dal controller -->
        <div>
            <input type="hidden" name="passeggeri" value="0">
            <input type="hidden" name="merci" value="0">
        </div>

        <div>
            <button type="submit">Aggiungi Volo</button>
        </div>
    </form>

    <!-- Messaggio di errore o successo -->
    <c:if test="${not empty sessionScope.messaggio}">
        <div class="success">${sessionScope.messaggio}</div>
    </c:if>
    <c:if test="${not empty sessionScope.errore}">
        <div class="error">${sessionScope.errore}</div>
    </c:if>

</body>
</html>