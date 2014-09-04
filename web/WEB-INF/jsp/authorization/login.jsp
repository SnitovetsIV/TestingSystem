<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.config" var="bundleConf" scope="session"/>
<fmt:setBundle basename="resources.labels" var="bundleLabel" scope="session"/>
<fmt:setBundle basename="resources.messages" var="bundleMess" scope="session"/>
<!DOCTYPE html>
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
        <h1><fmt:message key="labels.jsp.login.enter" bundle="${bundleLabel}"/><span class="orange-stop">.</span></h1>

        <form name="loginForm" action="controller" method="post">
            <c:if test="${not empty badMessage}">
                <div class="bad-message">
                    <fmt:message key="${badMessage}" bundle="${bundleMess}"/>
                </div>
            </c:if>
            <c:if test="${not empty goodMessage}">
                <div class="good-message">
                    <fmt:message key="${goodMessage}" bundle="${bundleMess}"/>
                </div>
            </c:if>
            <div class="input-field">
                <span class="icon username-icon fontawesome-user"></span>
                <input type="text" class="auth-input"
                       placeholder="<fmt:message key="labels.jsp.login.login" bundle="${bundleLabel}"/>"
                       name="login" value="${login}" required/><br/>
            </div>
            <div class="input-field">
                <span class="icon password-icon fontawesome-lock"></span>
                <input type="password" class="auth-input"
                       placeholder="<fmt:message key="labels.jsp.login.password" bundle="${bundleLabel}"/> "
                       name="password" required value=""><br/>
            </div>
            <button class="big-button" name="command" value="LOGIN">
                <fmt:message key="labels.jsp.login.enter" bundle="${bundleLabel}"/>
            </button>
        </form>
        <form name="registrationForm" action="controller" method="post">
            <button class="big-button" name="command" value="START_REGISTRATION">
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
