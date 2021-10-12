<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>This is the homepage!</p>
        <jsp:useBean id="now" class="java.util.Date"/>
        <fmt:formatDate value="${now}" dateStyle="long"/>
        <fmt:formatDate value="${now}" pattern="dd-MM-yyyy HH:mm:ss a z" />
    </body>
</html>
