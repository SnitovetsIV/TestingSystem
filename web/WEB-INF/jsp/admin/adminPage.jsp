<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.adminpage.title" bundle="${bundleLabel}"/></title>
    <link rel="stylesheet" href="css/general_style.css" type="text/css">
</head>
<body>
<div id="header">
    <c:import url="../common/header.jsp" charEncoding="utf-8"/>
</div>
<div class="menu">
    <c:import url="../common/adminMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.adminpage.header" bundle="${bundleLabel}"/></div>
    <h3><fmt:message key="labels.jsp.adminpage.hello" bundle="${bundleLabel}"/></h3><br/>
    <c:if test="${not empty badMessage}">
        <div class="bad-message">
            <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/>
        </div>
    </c:if>
    <c:if test="${not empty goodMessage}">
        <div class="good-message">
            <fmt:message key="${goodMessage}" bundle="${bundleMess}"/><br/>
        </div>
    </c:if>
</div>

<div id="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
