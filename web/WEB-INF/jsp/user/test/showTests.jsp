<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${locale}"/>
<link rel="stylesheet" type="text/css" href="/css/general_style.css"/>
<html>
<head>
    <title><fmt:message key="labels.jsp.showtests.title" bundle="${bundleLabel}"/></title>
</head>
<body>
<div id="header">
    <c:import url="../../common/header.jsp" charEncoding="utf-8"/>
</div>
<div class="user-menu">
    <c:import url="../../common/userMenu.jsp" charEncoding="utf-8"/>
</div>
<div class="main-div">
    <div class="header-page"><fmt:message key="labels.jsp.showtests.header" bundle="${bundleLabel}"/></div>
    <c:set var="listStep" scope="session" value="15"/>
    <div class="simpleTable">
        <table width="100%">
            <tr>
                <td>
                    <fmt:message key="labels.jsp.showtests.table.title.name" bundle="${bundleLabel}"/>
                </td>
                <td>
                    <fmt:message key="labels.jsp.showtests.table.title.description" bundle="${bundleLabel}"/>
                </td>
                <td>
                    <fmt:message key="labels.jsp.showtests.table.title.subject" bundle="${bundleLabel}"/>
                </td>
                <td>
                    <fmt:message key="labels.jsp.showtests.table.title.topics" bundle="${bundleLabel}"/>
                </td>
                <td>
                    <fmt:message key="labels.jsp.showtests.table.title.stat" bundle="${bundleLabel}"/>
                </td>
                <td>
                </td>
            </tr>
            <c:forEach var="test" items="${tests}" varStatus="status" begin="${startList}"
                       end="${startList + listStep - 1}">
                <tr>
                    <td>
                            ${test.name}
                    </td>
                    <td>
                            ${test.description}
                    </td>
                    <td>
                            ${test.subject}
                    </td>
                    <td>
                            ${test.topics}
                    </td>
                    <td>
                            ${test.stat}
                    </td>
                    <td>
                        <form name="performTestForm" action="controller" method="post">
                            <input type="hidden" name="command" value="TAKE_TEST"/>
                            <button name="test_id" value="${test.id}" class="take-test-button">
                                <fmt:message key="labels.jsp.showtests.table.test.take" bundle="${bundleLabel}"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <ctg:button-tag size="${tests.size()}" step="${listStep}" position="${startList}"/>
</div>
<div id="footer">
    <c:import url="../../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
