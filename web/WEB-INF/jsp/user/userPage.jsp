<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.userpage.title" bundle="${bundleLabel}"/></title>
    <link rel="stylesheet" href="css/general_style.css" type="text/css">
</head>
<body>
<div id="header">
    <c:import url="../common/header.jsp" charEncoding="utf-8"/>
</div>
<div class="user-menu">
    <c:import url="../common/userMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.userpage.header" bundle="${bundleLabel}"/></div>
    <h3><fmt:message key="labels.jsp.userpage.hello" bundle="${bundleLabel}"/></h3><br/>
    ${resultTest}<br/>
    <c:if test="${not empty changePassMess}">
        <div class="bad-message">
            <fmt:message key="${changePassMess}" bundle="${bundleMess}"/><br/>
        </div>
    </c:if>
    <c:if test="${not empty successChangePassMess}">
        <div class="good-message">
            <fmt:message key="${successChangePassMess}" bundle="${bundleMess}"/><br/>
        </div>
    </c:if>
</div>
<div id="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
