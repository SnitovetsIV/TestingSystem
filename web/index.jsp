<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty user and user.type eq 'ADMIN'}">
        <jsp:forward page="WEB-INF/jsp/admin/adminPage.jsp"/>
    </c:when>
    <c:when test="${not empty user and user.type eq 'USER'}">
        <jsp:forward page="WEB-INF/jsp/user/userPage.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:forward page="WEB-INF/jsp/authorization/login.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>