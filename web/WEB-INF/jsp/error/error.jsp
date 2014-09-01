<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<link rel="stylesheet" type="text/css" href="/css/error_style.css"/>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<div>
    <img class="image-error" src="../../../images/error_page_img.png"/>
</div>
<div class="main-div">
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.exception}
    <br/>
    Message from exception: ${pageContext.exception.localizedMessage}
    <br/>
    Error message: ${errorMessage}
    <form name="backErrorForm" action="controller" method="post">
        <button name="command" value="RETURN_FROM_ERROR">
            Return to main page
        </button>
    </form>
</div>
<div class="back-div">

</div>
</body>
</html>
