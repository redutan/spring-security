<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Hello boot</title>
</head>
<body>
<sec:authorize access="isAnonymous()">
    <br/>로그아웃상태<br/>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <br/>로그인상태<br/>
</sec:authorize>

index<br/>
<a href="/admin">어드민</a><br/>
<a href="/user">사용자</a><br/>
<a href="/registrationForm">회원가입</a><br/>
<a href="/getUserMessage">사용자메세지</a><br/>
<a href="/getPrivateMessage">비밀메세지</a><br/>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
