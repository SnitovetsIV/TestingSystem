<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.addanswers.title" bundle="${bundleLabel}"/></title>
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
    <div class="header-page"><fmt:message key="labels.jsp.addanswers.header" bundle="${bundleLabel}"/></div>
    <div class="add-question-field">
        <form name="addAnswersForm" action="controller" method="post">
            <c:if test="${not empty badMessage}">
                <div class="bad-message">
                    <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/>
                </div>
            </c:if>
            <h3><fmt:message key="labels.jsp.addanswers.answer.input" bundle="${bundleLabel}"/></h3>
            <ol>
                <c:forEach var="answerItem" begin="1" end="${countAnswers}" varStatus="status">
                    <li>
                        <span class='input'>
                            <input type="checkbox" name="corrects" class="input-checkbox-taketest"
                                   value="${answerItem}"> <input type="text" class="username"
                                                                 placeholder="<fmt:message key="labels.jsp.addanswers.answer.text" bundle="${bundleLabel}"/>"
                                                                 name="answers" value="" required><br/>
                        </span>
                    </li>
                </c:forEach>
            </ol>
            <button name="command" value="SAVE_NEW_QUESTION">
                <fmt:message key="labels.jsp.addanswers.save" bundle="${bundleLabel}"/>
            </button>
        </form>
        <form name="cancelCreateTestForm" action="controller" method="post">
            <button name="command" value="CANCEL_CREATE_QUESTION">
                <fmt:message key="labels.jsp.addanswers.cancel" bundle="${bundleLabel}"/>
            </button>
        </form>
    </div>
</div>

<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>