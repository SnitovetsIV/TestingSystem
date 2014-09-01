<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.addquestions.title" bundle="${bundleLabel}"/></title>
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
    <div class="header-page"><fmt:message key="labels.jsp.addquestions.header" bundle="${bundleLabel}"/></div>
    <div class="add-question-field">
        <c:if test="${not empty badMessage}">
            <div class="bad-message">
                <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/><br/>
            </div>
        </c:if>
        <br/>

        <form name="chooseQuestionsForm" action="controller" method="post">
            <c:forEach var="question" items="${questionsTopics}" varStatus="status">
                <div class="check-box-input-q" style="height: auto">
                    <input id="${question}" class="input-create-choose" type="checkbox" name="questionsTest"
                           value="${question.id}"/>
                    <label class="label-checkbox" for="${question}">
                        <br/>

                        <pre class="code-style">${question.description}<br/>
                        </pre>
                        <ol>
                            <c:forEach var="answer" items="${question.answers}" varStatus="status">
                                <li>${answer.description} (${answer.isCorrect()})</li>

                            </c:forEach>
                        </ol>
                    </label><br/>
                    <hr/>
                </div>
            </c:forEach>
            <button name="command" value="SAVE_NEW_TEST">
                <fmt:message key="labels.jsp.addquestions.save" bundle="${bundleLabel}"/>
            </button>
        </form>
        <form name="cancelCreateTestForm" action="controller" method="post">
            <button name="command" value="CANCEL_CREATE_TEST">
                <fmt:message key="labels.jsp.addquestions.cancel" bundle="${bundleLabel}"/>
            </button>
        </form>
    </div>
</div>

<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>