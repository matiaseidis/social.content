<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Post</title>
</head>
<body>
<form action="../update/${post.id}" method=POST>
<input type="text" name="title" value="${post.title}">
<p></p>
<textarea rows="20" cols="70" name="body" value="${post.body}"></textarea>
<p></p>
<input type="submit">
</form>
</body>
</html>