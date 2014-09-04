<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>user menu</title>
</head>
<body>
<form name="userMenu" action="controller" method="post">
    <button class="big-button" name="command" value="MAIN_PAGE">
        <fmt:message key="labels.jsp.menu.user.mainpage" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button class="big-button" name="command" value="PERSONAL_ACCOUNT">
        <fmt:message key="labels.jsp.menu.user.personalaccount" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button class="big-button" name="command" value="ALL_TESTS">
        <fmt:message key="labels.jsp.menu.user.test.all" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button class="big-button" name="command" value="SUBJECT_TESTS">
        <fmt:message key="labels.jsp.menu.user.test.subject" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button class="big-button" name="command" value="COMPLETED_TESTS">
        <fmt:message key="labels.jsp.menu.user.test.completed" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button class="big-button" name="command" value="LOGOUT">
        <fmt:message key="labels.jsp.menu.user.logout" bundle="${bundleLabel}"/>
    </button>
</form>
</body>
</html>
