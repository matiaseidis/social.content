<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit User</title>
</head>
<body>
<h1>Edit User</h1>
	<form action="create" method=POST>
		<input type="text" name="userName" value="${user.userName}"> 
		<br />
		<input
			type="password" name="password" value="${user.password}">
		<ul>
			<c:forEach items="${roles}" var="item">
				<li>${item}</li>
			</c:forEach>
		</ul>

		<ul>
			<c:forEach items="${posts}" var="item">
				<li>${item.title}</li>
			</c:forEach>
		</ul>

		<ul>
			<c:forEach items="${tags}" var="item">
				<li>${item.tagName}</li>
			</c:forEach>
		</ul>

		<input type="submit">
	</form>
</body>
</html>