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
<div class="menu">
    <c:import url="../common/userMenu.jsp" charEncoding="utf-8"/>
</div>
<div id="page-wrapper">
    <div class="main-div">
        <div class="header-page"><fmt:message key="labels.jsp.personalaccount.header" bundle="${bundleLabel}"/></div>
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
        <div class="center-wrapper">
            <h3><fmt:message key="labels.jsp.personalaccount.change.data" bundle="${bundleLabel}"/></h3><br/>

            <form name="dataForm" action="controller" method="post">
                <div class='input-field'>
                    <span class='icon username-icon fontawesome-user'></span>
                    <input type="text" class="big-input"
                           placeholder="<fmt:message key="labels.jsp.personalaccount.change.data.name" bundle="${bundleLabel}"/>"
                           name="name" value="${user.name}"><br/>
                </div>
                <div class='input-field'>
                    <span class='icon username-icon fontawesome-user'></span>
                    <input type="text" class="big-input"
                           placeholder="<fmt:message key="labels.jsp.personalaccount.change.data.surname" bundle="${bundleLabel}"/>"
                           name="surname" value="${user.surname}"><br/>
                </div>
                <button name="command" value="SAVE_PERSONAL_DATA" class="big-button">
                    <fmt:message key="labels.jsp.personalaccount.change.data.submit" bundle="${bundleLabel}"/>
                </button>
            </form>
        </div>
        <div class="center-wrapper">
            <h3><fmt:message key="labels.jsp.personalaccount.change.password" bundle="${bundleLabel}"/></h3><br/>

            <form name="changePasswordForm" action="controller" method="post">
                <div class='input-field'>
                    <span class='icon password-icon fontawesome-lock'></span>
                    <input type="password" class="big-input"
                           placeholder="<fmt:message key="labels.jsp.personalaccount.change.password.oldpassword" bundle="${bundleLabel}"/>*"
                           name="oldPassword" required value=""><br/>
                </div>
                <div class='input-field'>
                    <span class='icon password-icon fontawesome-lock'></span>
                    <input type="password" class="big-input"
                           placeholder="<fmt:message key="labels.jsp.personalaccount.change.password.newpassword" bundle="${bundleLabel}"/>*"
                           name="password" required value=""><br/>
                </div>
                <div class='input-field'>
                    <span class='icon password-icon fontawesome-lock'></span>
                    <input type="password" class="big-input"
                           placeholder="<fmt:message key="labels.jsp.personalaccount.change.password.repeatpassword" bundle="${bundleLabel}"/>*"
                           name="repeatPassword" required value=""><br/>
                </div>
                <button name="command" value="CHANGE_PASSWORD" class="big-button">
                    <fmt:message key="labels.jsp.personalaccount.change.password.submit" bundle="${bundleLabel}"/>
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