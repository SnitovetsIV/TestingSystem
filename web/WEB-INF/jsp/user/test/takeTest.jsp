<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.taketest.title" bundle="${bundleLabel}"/></title>
    <link rel="stylesheet" href="css/general_style.css" type="text/css">
</head>
<body>
<div id="header">
    <c:import url="../../common/header.jsp" charEncoding="utf-8"/>
</div>
<div class="menu">
    <c:import url="../../common/userMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.taketest.header" bundle="${bundleLabel}"/></div>
    <h3>${test.name}</h3>
    <c:if test="${not empty badMessage}">
        <div class="bad-message">
            <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/>
        </div>
    </c:if>
    <div>
        <b> <fmt:message key="labels.jsp.taketest.question.number"
                         bundle="${bundleLabel}"/>: ${currentQuestionNumber}/${allCountQuestions}<br/></b>
        <fmt:message key="labels.jsp.taketest.topic" bundle="${bundleLabel}"/>: ${question.topic}<br/>
        <fmt:message key="labels.jsp.taketest.question.description" bundle="${bundleLabel}"/>: <br/>
        <pre class="code-style">${question.description}</pre>
        <br/>
        <fmt:message key="labels.jsp.taketest.answers" bundle="${bundleLabel}"/>:<br/>
        <hr/>
        <div class="check-box-input" style="height: auto">
            <form name="takeTestForm" action="controller" method="post">
                <c:forEach var="answer" items="${question.answers}" varStatus="status">
                    <input id="answers" type="checkbox"
                           name="answers" value="${answer.id}"/>
                    <label class="label-checkbox" for="answers">${answer.description} -
                        (${answer.isCorrect()})<br/></label>
                </c:forEach>
                <button class="big-button" name="command" value="ANSWER_QUESTION">
                    <fmt:message key="labels.jsp.taketest.answer" bundle="${bundleLabel}"/>
                </button>
            </form>
            <form name="cancelTakeTestForm" action="controller" method="post">
                <button class="big-button" name="command" value="CANCEL_TAKE_TEST">
                    <fmt:message key="labels.jsp.taketest.test.cancel" bundle="${bundleLabel}"/>
                </button>
            </form>
        </div>
    </div>
</div>

<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>