<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<link rel="stylesheet" type="text/css" href="/css/general_style.css"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.personalaccount.title" bundle="${bundleLabel}"/></title>
</head>
<body>
<div id="header">
    <c:import url="../common/header.jsp" charEncoding="utf-8"/>
</div>
<div class="user-menu">
    <c:import url="../common/userMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.personalaccount.header" bundle="${bundleLabel}"/></div>
    <div class="change-field">
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
        <h3><fmt:message key="labels.jsp.personalaccount.change.data" bundle="${bundleLabel}"/></h3><br/>

        <form name="dataForm" action="controller" method="post">
            <span class='input'>
                <span class='icon username-icon fontawesome-user'></span>
                <input type="text" class="username"
                       placeholder="<fmt:message key="labels.jsp.personalaccount.change.data.name" bundle="${bundleLabel}"/>"
                       name="name" value="${user.name}"><br/>
            </span>
            <span class='input'>
                <span class='icon username-icon fontawesome-user'></span>
                <input type="text" class="username"
                       placeholder="<fmt:message key="labels.jsp.personalaccount.change.data.surname" bundle="${bundleLabel}"/>"
                       name="surname" value="${user.surname}"><br/>
            </span>
            <button name="command" value="SAVE_PERSONAL_DATA">
                <fmt:message key="labels.jsp.personalaccount.change.data.submit" bundle="${bundleLabel}"/>
            </button>

        </form>
    </div>
    <div class="change-field">
        <h3><fmt:message key="labels.jsp.personalaccount.change.password" bundle="${bundleLabel}"/></h3><br/>
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
        <form name="changePasswordForm" action="controller" method="post">
            <span class='input'>
                <span class='icon password-icon fontawesome-lock'></span>
                <input type="password" class='password'
                       placeholder="<fmt:message key="labels.jsp.personalaccount.change.password.oldpassword" bundle="${bundleLabel}"/> "
                       name="oldpassword" required value=""><br/>
            </span>
            <span class='input'>
                <span class='icon password-icon fontawesome-lock'></span>
                <input type="password" class='password'
                       placeholder="<fmt:message key="labels.jsp.personalaccount.change.password.newpassword" bundle="${bundleLabel}"/> "
                       name="password" required value=""><br/>
            </span>
            <span class='input'>
                <span class='icon password-icon fontawesome-lock'></span>
                <input type="password" class='password'
                       placeholder="<fmt:message key="labels.jsp.personalaccount.change.password.repeatpassword" bundle="${bundleLabel}"/> "
                       name="repeatPassword" required value=""><br/>
            </span>
            <button name="command" value="CHANGE_PASSWORD">
                <fmt:message key="labels.jsp.personalaccount.change.password.submit" bundle="${bundleLabel}"/>
            </button>
        </form>
    </div>
</div>
<div id="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>