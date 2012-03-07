<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Social Content</h2>
<h2>${loggedIn}</h2>
   <sec:authorize access='isAnonymous()'>

<jsp:include page="/login.jsp"></jsp:include>
        </sec:authorize>
<h2>Social Content</h2>
<br />
Estas siguiendo a Usuarios:
<ul>
<c:forEach var="followed" items="${followedUsers}">
<li>${followed.userName}</li>
</c:forEach>
</ul>
<br />
Estas siguiendo a Tags:
<ul>
<c:forEach var="followed" items="${followedTags}">
<li>${followed.tagName}</li>
</c:forEach>
</ul>

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
					<td>following users</td>
					<td>following tags</td>
					<td>followed by</td>
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
						<td>${fn:length(user.followedUsers)}</td>
						<td>${fn:length(user.followedTags)}</td>
						<td>${fn:length(user.followedBy)}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<p>Posts:</p>
	<div>
		<c:forEach items="${users}" var="user">
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
