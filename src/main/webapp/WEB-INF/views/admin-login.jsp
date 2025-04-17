<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login Admin</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h2>Login Amministratore</h2>
    <form action="<c:url value='/admin/login' />" method="post">
        <label>Username: <input type="text" name="username" /></label><br/>
        <label>Password: <input type="password" name="password" /></label><br/>
        <button type="submit">Login</button>
    </form>

    <c:if test="${not empty errore}">
        <p style="color:red">${errore}</p>
    </c:if>
</body>
</html>