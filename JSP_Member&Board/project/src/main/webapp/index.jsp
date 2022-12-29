<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>
<h1>My First Project Board & Member</h1>
<c:if test="${ses.email ne null && ses.email ne ''}">
 ${email} login 하였습니다.<br>
 계정생성일 : ${ses.reg_at}<br>
 마지막 접속 : ${ses.last_login}<br>
 
 <a href="/mem/modify"> <button type="button" class="btn btn-outline-secondary">회원정보수정</button></a> 
 <a href="/mem/logout?email=${ses.email}"> <button type="button" class="btn btn-outline-secondary">로그아웃</button></a><br>
</c:if> 

<c:if test="${ses.email ne null && ses.email ne ''}">
 <a href="/brd/register"> Board Register </a>
</c:if> 

<a href="/brd/pageList">Bo ard List</a>
<a href="/mem/login">Log in</a>
<a href="/mem/join">Sing Up</a>
<a href="/mem/list">Member List</a>

<c:if test="${ses.email ne null && ses.email ne ''}">
 <a href="/mem/remove?email=${ses.email}">회원탈퇴</a>
</c:if>

</body>
</html>