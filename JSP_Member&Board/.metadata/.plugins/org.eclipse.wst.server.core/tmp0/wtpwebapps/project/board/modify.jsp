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
<h1>Board Modify Page</h1>
<form action="/brd/update" method="post" enctype="multipart/form-data">
bno : <input type="text" name="bno" value="${bvo.bno}" readonly><br>
title : <input type="text" name="title" value="${bvo.title}"><br> 
content : <textarea rows="3" cols="30" name="content">${bvo.content}</textarea><br>
image_file : 
<input type="hidden" name="image_file" value="${bvo.image_file}">
<input type="file" name="new_file" accept="image/png, image/jpg, image/jpeg, image/gif">
<button type="submit">수정</button>
</form>
</body>
</html>