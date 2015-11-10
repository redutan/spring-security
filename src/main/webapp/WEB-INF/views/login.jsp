<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h1>로그인 페이지</h1>
<c:url value="/login" var="loignUrl"/>
<form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <p>
            Invalid username and password
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>
            You have beed logged out.
        </p>
    </c:if>
    <p>
        <label form="username">UserName</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label form="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn">Login in</button>
</form>
</body>
</html>