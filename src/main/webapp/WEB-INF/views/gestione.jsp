<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Admin - Gestione Voli</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>Gestione Voli</h2>
	
	<c:if test="${not empty messaggio}">
    	<div class="success-message">${messaggio}</div>
	</c:if>
	
	<c:if test="${not empty errore}">
	    <div class="error-message">${errore}</div>
	</c:if>
	<br>
	
    <div class="admin-actions">
        <a href="<c:url value='/admin/aggiungi-volo'/>"><button>Inserisci nuovo volo</button></a>
        <a href="<c:url value='/admin/modifica-aereo'/>"><button>Modifica aereo per un volo</button></a>
        <a href="<c:url value='/admin/cancella-volo'/>"><button>Cancella volo</button></a>
        <a href="<c:url value='/admin/elenco'/>"><button>Elenco voli imminenti</button></a>
    </div>

    <br>

    <a href="<c:url value='/admin/logout'/>"><button>Logout</button></a> 
</body>
</html>
