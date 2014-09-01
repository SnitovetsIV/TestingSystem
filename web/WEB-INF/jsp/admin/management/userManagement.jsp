<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${locale}"/>
<link rel="stylesheet" type="text/css" href="/css/general_style.css"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.usermanagement.title" bundle="${bundleLabel}"/></title>
</head>
<body>

<div id="header">
    <c:import url="../../common/header.jsp" charEncoding="utf-8"/>
</div>
<div class="user-menu">
    <c:import url="../../common/adminMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.usermanagement.header" bundle="${bundleLabel}"/></div>
    <c:set var="listStep" scope="session" value="15"/>
    <div class="simpleTable">
        <table width="100%">
            <tr>
                <td>
                    <fmt:message key="labels.jsp.usermanagement.table.title.login" bundle="${bundleLabel}"/>
                </td>
                <td>
                    <fmt:message key="labels.jsp.usermanagement.table.title.name" bundle="${bundleLabel}"/>
                </td>
                <td>
                    <fmt:message key="labels.jsp.usermanagement.table.title.surname" bundle="${bundleLabel}"/>
                </td>
                <td>
                    <fmt:message key="labels.jsp.usermanagement.table.title.counttest" bundle="${bundleLabel}"/>
                </td>
                <td>
                    <fmt:message key="labels.jsp.usermanagement.table.title.stat" bundle="${bundleLabel}"/>
                </td>
                <td>
                </td>
            </tr>
            <c:forEach var="user" items="${users}" varStatus="status" begin="${startList}"
                       end="${startList + listStep - 1}">
                <tr>
                    <td>
                            ${user.login}
                    </td>
                    <td>
                            ${user.name}
                    </td>
                    <td>
                            ${user.surname}
                    </td>
                    <td>
                            ${user.countCompletedTests}
                    </td>
                    <td>
                            ${user.statistic}%
                    </td>
                    <td>
                        <form name="clearStatForm" action="controller" method="post">
                            <input type="hidden" name="command" value="CLEAR_STAT"/>
                            <button name="userId" value="${user.id}" class="take-test-button">
                                <fmt:message key="labels.jsp.usermanagement.table.stat.clear" bundle="${bundleLabel}"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <ctg:button-tag size="${users.size()}" step="${listStep}" position="${startList}"/>
</div>
<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
