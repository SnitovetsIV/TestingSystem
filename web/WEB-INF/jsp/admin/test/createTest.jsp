<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.createtest.title" bundle="${bundleLabel}"/></title>
    <link rel="stylesheet" href="css/general_style.css" type="text/css">
</head>
<body>
<div id="header">
    <c:import url="../../common/header.jsp" charEncoding="utf-8"/>
</div>
<div class="user-menu">
    <c:import url="../../common/adminMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.createtest.header" bundle="${bundleLabel}"/></div>
    <div class="change-field">
        <c:if test="${not empty badMessage}">
            <div class="bad-message">
                <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/>
            </div>
        </c:if>
        <form name="createTestForm" action="controller" method="post" autocomplete="off">
            <h3><fmt:message key="labels.jsp.createtest.name" bundle="${bundleLabel}"/></h3><br/>
            <span class='input'>
                    <span class='icon username-icon fontawesome-text'></span>
                    <input type="text" name="testName" required value=""
                           placeholder="<fmt:message key="labels.jsp.createtest.name" bundle="${bundleLabel}"/>*:">
            </span>

            <h3><fmt:message key="labels.jsp.createtest.description" bundle="${bundleLabel}"/></h3><br/>
            <span class='input'>
                    <span class='icon username-icon fontawesome-text'></span>
                    <input type="text" name="testDescription" value=""
                           placeholder="<fmt:message key="labels.jsp.createtest.description" bundle="${bundleLabel}"/>:">
            </span>
            <button name="command" value="START_ADD_QUESTIONS">
                <fmt:message key="labels.jsp.createtest.questions.add" bundle="${bundleLabel}"/>
            </button>
        </form>
        <form name="cancelCreateForm" action="controller" method="post" autocomplete="off">
            <button name="command" value="CANCEL_CREATE_TEST">
                <fmt:message key="labels.jsp.createtest.cancel" bundle="${bundleLabel}"/>
            </button>
        </form>
    </div>
</div>

<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>