<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Users:</p>
	<div>
		<ul>
			<c:forEach var="user" items="${model.users}" >
				<li>${user.userName}</li>
			</c:forEach>
		</ul>
	</div>
	<p>Posts:</p>
	<div>
		<c:forEach items="${model.users}" var="user">
			<p>${user.userName} posts:</p>
			<ul>
				<c:forEach items="${user.posts}" var="post">
					<li>${post.title}</li>
				</c:forEach>
			</ul>
		</c:forEach>
	</div>
	${message}
</body>
</html>
