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
	<jsp:include page="/admin/menu.jsp"></jsp:include>
	<p>Users (${fn:length(users)}):</p>
	<div>
		<table>
			<thead>
				<tr>
					<td>name</td>
					<td>pass</td>
					<td>roles</td>
					<td>posts</td>
					<td>videos</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.userName}</td>
						<td>${user.password}</td>
						<td>${fn:length(user.roles)}</td>
						<td>${fn:length(user.posts)}</td>
						<td><a href="<c:out value='${user.userName}' ></c:out>/videos" >${fn:length(user.videos)}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>