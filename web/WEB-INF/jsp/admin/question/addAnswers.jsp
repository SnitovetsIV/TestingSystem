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
<div class="menu">
    <c:import url="../../common/adminMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.addanswers.header" bundle="${bundleLabel}"/></div>
    <div class="center-wrapper">
        <form name="addAnswersForm" action="controller" method="post">
            <c:if test="${not empty badMessage}">
                <div class="bad-message">
                    <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/>
                </div>
            </c:if>
            <fmt:message key="labels.jsp.addanswers.topic" bundle="${bundleLabel}"/>: ${topicName}<br/>
            <fmt:message key="labels.jsp.addanswers.question.description" bundle="${bundleLabel}"/>: <br/>
            <pre class="code-style">${questionDescription}</pre>
            <h3><fmt:message key="labels.jsp.addanswers.answer.input" bundle="${bundleLabel}"/></h3>
            <ol>
                <c:forEach var="answerItem" begin="1" end="${countAnswers}" varStatus="status">
                    <li>
                        <div class='input-field'>
                            <input type="checkbox" name="correctAnswers"
                                   value="${answerItem}"/><br/>
                            <textarea name="answers" required rows="5" cols="40"
                                      placeholder="<fmt:message key="labels.jsp.addanswers.answer.text"
                              bundle="${bundleLabel}"/>"></textarea>
                        </div>
                    </li>
                    <hr/>
                </c:forEach>
            </ol>
            <button class="big-button" name="command" value="SAVE_NEW_QUESTION">
                <fmt:message key="labels.jsp.addanswers.save" bundle="${bundleLabel}"/>
            </button>
        </form>
        <form name="cancelCreateTestForm" action="controller" method="post">
            <button class="big-button" name="command" value="CANCEL_CREATE_QUESTION">
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