<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.config" var="bundleConf" scope="session"/>
<fmt:setBundle basename="resources.labels" var="bundleLabel" scope="session"/>
<fmt:setBundle basename="resources.messages" var="bundleMess" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Header</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div class="show-role">
    <c:if test="${not empty user}">
        <fmt:message key="labels.jsp.role.message.beforelogin" bundle="${bundleLabel}"/>
        ${user.login}.
        <fmt:message key="labels.jsp.role.message.afterlogin" bundle="${bundleLabel}"/>
        <c:choose>
            <c:when test="${user.type eq 'ADMIN'}">
                <fmt:message key="labels.jsp.role.admin" bundle="${bundleLabel}"/>.
            </c:when>
            <c:when test="${user.type eq 'USER'}">
                <fmt:message key="labels.jsp.role.user" bundle="${bundleLabel}"/>.
            </c:when>
            <c:otherwise>
                <fmt:message key="labels.jsp.role.unknown" bundle="${bundleLabel}"/>.
            </c:otherwise>
        </c:choose>
    </c:if>
</div>
<div class="header-text">
    <fmt:message key="labels.jsp.login.header" bundle="${bundleLabel}"/>
</div>
<div class="chose-lang">
    <form name="changeLangForm" action="controller" method="post">
        <input type="hidden" name="command" value="CHANGE_LANGUAGE"/>
        <input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
        <input class="dropdown-toggle" type="text">

        <div class="dropdown-text"><fmt:message key="labels.jsp.choose.language" bundle="${bundleLabel}"/></div>
        <ul class="dropdown-content">
            <li>
                <button name="lang" value="ru">
                    <fmt:message key="config.jsp.label.language.ru" bundle="${bundleConf}"/>
                </button>
                <br/>
            </li>
            <li>
                <button name="lang" value="en">
                    <fmt:message key="config.jsp.label.language.en" bundle="${bundleConf}"/>
                </button>
            </li>
        </ul>
    </form>
</div>

</body>
</html>
