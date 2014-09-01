<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>admin menu</title>
</head>
<body>
<form name="userMenu" action="controller" method="post">
    <button name="command" value="MAIN_ADMIN_PAGE">
        <fmt:message key="labels.jsp.menu.admin.mainpage" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button name="command" value="START_CREATE_TEST">
        <fmt:message key="labels.jsp.menu.admin.test.create" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button name="command" value="START_CREATE_QUESTION">
        <fmt:message key="labels.jsp.menu.admin.question.create" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button name="command" value="USER_MANAGEMENT">
        <fmt:message key="labels.jsp.menu.admin.user.management" bundle="${bundleLabel}"/>
    </button>
    <br/>
    <button name="command" value="LOGOUT">
        <fmt:message key="labels.jsp.menu.admin.logout" bundle="${bundleLabel}"/>
    </button>
</form>
</body>
</html>
