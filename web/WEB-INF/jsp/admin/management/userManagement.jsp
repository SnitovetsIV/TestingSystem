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
<div class="menu">
    <c:import url="../../common/adminMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.usermanagement.header" bundle="${bundleLabel}"/></div>
    <c:if test="${not empty badMessage}">
        <div class="bad-message">
            <fmt:message key="${badMessage}" bundle="${bundleMess}"/>
        </div>
    </c:if>
    <c:if test="${not empty goodMessage}">
        <div class="good-message">
            <fmt:message key="${goodMessage}" bundle="${bundleMess}"/>
        </div>
    </c:if>
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
                    <td class="button-td">
                        <form name="clearStatForm" action="controller" method="post">
                            <input type="hidden" name="command" value="CLEAR_STAT"/>
                            <button name="userId" value="${user.id}" class="table-button">
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
