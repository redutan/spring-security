<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Hello boot</title>
</head>
<body>
<h1>회원가입 폼</h1>
<form method="post" action="/register">
    아이디 : <input type="text" name="username"/><br/>
    비밀번호 : <input type="text" name="password"/><br/>
    권한 : <input type="text" name="role" value="ROLE_ADMIN"/><br/>
    닉네임 : <input type="text" name="nick" /><br/>
    <input type="submit" value="확인"/>
    <sec:csrfInput/>
</form>
</body>
</html>
