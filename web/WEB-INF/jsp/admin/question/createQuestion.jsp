<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.createquestion.title" bundle="${bundleLabel}"/></title>
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
    <div class="header-page"><fmt:message key="labels.jsp.createquestion.header" bundle="${bundleLabel}"/></div>
    <div class="change-field">
        <form name="createTestForm" action="controller" method="post" autocomplete="off">
            <h3><fmt:message key="labels.jsp.createquestion.description" bundle="${bundleLabel}"/></h3><br/><br/>
            <c:if test="${not empty badMessage}">
                <div class="bad-message">
                    <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/>
                </div>
            </c:if>
            <span class='input'>
                    <textarea type="text" name="questionDescription"
                              value="${questionDescription}" required rows="8" cols="50"
                              placeholder="<fmt:message key="labels.jsp.createquestion.description"
                              bundle="${bundleLabel}"/>"></textarea>
            </span>

            <h3><fmt:message key="labels.jsp.createquestion.answers.count" bundle="${bundleLabel}"/></h3><br/><br/>
            <input type="number" min="2" max="10" name="countAnswers" required>
            <button name="command" value="START_ADD_ANSWERS">
                <fmt:message key="labels.jsp.createquestion.answers.add" bundle="${bundleLabel}"/>
            </button>
        </form>
        <form name="cancelCreateForm" action="controller" method="post" autocomplete="off">
            <button name="command" value="CANCEL_CREATE_QUESTION">
                <fmt:message key="labels.jsp.createquestion.cancel" bundle="${bundleLabel}"/>
            </button>
        </form>
    </div>
</div>

<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
