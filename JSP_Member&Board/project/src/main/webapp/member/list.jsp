<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Member List Page</h1>
<table border="1">
	<tr>
		<th>EMAIL</th>
		<th>PASSWORD</th>
		<th>NICK_NAME</th>
		<th>REG_AT</th>
		<th>LAST_LOGIN</th>
	</tr>
	<c:forEach items="${list}" var="mvo">
	<tr>
		<th>${mvo.email}</th>
		<th>${mvo.password}</th>
		<th>${mvo.nick_name}</th>
		<th>${mvo.reg_at}</th>
		<th>${mvo.last_login}</th>
	</tr>
	</c:forEach>
</table>

<a href="/">index..</a>
</body>
</html>