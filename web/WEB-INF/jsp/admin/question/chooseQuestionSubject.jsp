<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.choosequestionsubject.title" bundle="${bundleLabel}"/></title>
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
    <div class="header-page"><fmt:message key="labels.jsp.choosequestionsubject.header" bundle="${bundleLabel}"/></div>
    <div class="center-wrapper">
        <c:forEach var="subject" items="${subjects}" varStatus="status">
            <form name="chooseSubjectForm" action="controller" method="post">
                <input type="hidden" name="command" value="CHOOSE_QUESTION_SUBJECT"/>
                <button class="big-button" name="subjectName" value="${subject}">
                        ${subject}
                </button>
            </form>
        </c:forEach>
    </div>
</div>

<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>