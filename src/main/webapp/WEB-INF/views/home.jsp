<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Benvenuto</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="top-right">
        <a href="<c:url value='/admin/login'/>"><button class="button">Login</button></a>
    </div>

    <div class="main-content">
        <h1>Benvenuto nel sistema Aeroporti</h1>
       	<br> 
        <a href="<c:url value='/cerca'/>"><button class="button">Ricerca Voli</button></a>
    </div>
</body>
</html>