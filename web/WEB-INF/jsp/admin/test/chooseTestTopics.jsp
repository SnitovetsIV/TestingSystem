<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.choosetopics.title" bundle="${bundleLabel}"/></title>
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
    <div class="header-page"><fmt:message key="labels.jsp.choosetopics.header" bundle="${bundleLabel}"/></div>
    <c:if test="${not empty badMessage}">
        <div class="bad-message">
            <fmt:message key="${badMessage}" bundle="${bundleMess}"/><br/><br/>
        </div>
    </c:if>
    <div class="center-wrapper">
        <form name="chooseTopicsForm" action="controller" method="post">
            <c:forEach var="topic" items="${topics}" varStatus="status">
                <div class="check-box-input">
                    <input id="${topic}" class="input-create-choose" type="checkbox" name="testTopics"
                           value="${topic}"/>
                    <label class="label-checkbox" for="${topic}"> ${topic}</label> <BR>
                </div>
            </c:forEach>
            <button class="big-button" name="command" value="CHOOSE_TOPICS">
                <fmt:message key="labels.jsp.choosetopics.submit" bundle="${bundleLabel}"/>
            </button>
        </form>
    </div>
</div>

<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
