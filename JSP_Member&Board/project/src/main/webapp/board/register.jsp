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
<h1>Board Register Page</h1>
	<form action="/brd/insert" method="post" enctype="multipart/form-data">
		title : <input type="text" name="title"><br>
		writer : <input type="text" name="writer" value="${ses.email}" readonly><br>
		content : <textarea rows="3" cols="30" name="content"></textarea><br>
		image_file : <input type="file" id="file" name="image_file" 
		accept="image/png, image/jpg, image/jpeg, image/gif">
		<button type="submit">등록</button>
	</form>
</body>
</html>