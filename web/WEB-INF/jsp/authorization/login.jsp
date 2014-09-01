<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.config" var="bundleConf" scope="session"/>
<fmt:setBundle basename="resources.labels" var="bundleLabel" scope="session"/>
<fmt:setBundle basename="resources.messages" var="bundleMess" scope="session"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.login.title" bundle="${bundleLabel}"/></title>
    <link rel="stylesheet" href="css/general_style.css" type="text/css">
</head>
<body>
<div id="header">
    <c:import url="../common/header.jsp" charEncoding="utf-8"/>
</div>
<div id="page-wrapper">
    <div id="login">
        <h1><fmt:message key="labels.jsp.login.enter" bundle="${bundleLabel}"/><span class='orangestop'>.</span></h1>

        <form name="loginForm" action="controller" method="post" autocomplete="off">
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
            <span class='input'>
                <span class='icon username-icon fontawesome-user'></span>
                <input type="text" class="username"
                       placeholder="<fmt:message key="labels.jsp.login.login" bundle="${bundleLabel}"/>"
                       name="login" value="" required><br/>
            </span>
            <span class='input'>
                <span class='icon password-icon fontawesome-lock'></span>
                <input type="password" class='password'
                       placeholder="<fmt:message key="labels.jsp.login.password" bundle="${bundleLabel}"/> "
                       name="password" required value=""><br/>
            </span>
            <button name="command" value="LOGIN">
                <fmt:message key="labels.jsp.login.enter" bundle="${bundleLabel}"/>
            </button>
        </form>
        <form name="registrationForm" action="controller" method="post">
            <button name="command" value="START_REGISTRATION">
                <fmt:message key="labels.jsp.login.registration" bundle="${bundleLabel}"/>
            </button>
        </form>
    </div>
</div>
<div id="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
