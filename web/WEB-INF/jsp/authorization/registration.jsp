<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.registration.title" bundle="${bundleLabel}"/></title>
    <link rel="stylesheet" href="css/general_style.css" type="text/css">

</head>
<body>
<div id="header">
    <c:import url="../common/header.jsp" charEncoding="utf-8"/>
</div>
<div id="page-wrapper">
    <div id="loginR">
        <h1><fmt:message key="labels.jsp.registration.header" bundle="${bundleLabel}"/><span class='orangestop'>.</span>
        </h1>

        <div class="registration-field">
            <form name="registrationForm" action="controller" method="post" autocomplete="off">
                <c:if test="${not empty badMessage}">
                    <div class="bad-message">
                        <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/>
                    </div>
                </c:if>
            <span class='input'>
                <span class='icon username-icon fontawesome-user'></span>
                <input type="text" name="login" required value=""
                       placeholder="<fmt:message key="labels.jsp.registration.login" bundle="${bundleLabel}"/>*:"><br/>
            </span>
            <span class='input'>
                <span class='icon password-icon fontawesome-lock'></span>
                <input type="password" name="password" required value=""
                       placeholder="<fmt:message key="labels.jsp.registration.password" bundle="${bundleLabel}"/>*:"> <br/>
            </span>
            <span class='input'>
                <span class='icon password-icon fontawesome-lock'></span>
                <input type="password" name="repeatPassword" required value=""
                       placeholder="<fmt:message key="labels.jsp.registration.repeatpassword" bundle="${bundleLabel}"/>*:"> <br/>
            </span>
                <button name="command" value="REGISTRATION">
                    <fmt:message key="labels.jsp.registration.enter" bundle="${bundleLabel}"/>
                </button>
            </form>
            <form name="BackRegistrationForm" action="controller" method="post" autocomplete="off">
                <button name="command" value="BACK_REGISTRATION" class="regBack">
                    <fmt:message key="labels.jsp.registration.back" bundle="${bundleLabel}"/>
                </button>
            </form>
        </div>
    </div>
</div>
<div id="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
