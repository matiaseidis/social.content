<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perfil de usuario</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<jsp:include page="/barrita.jsp"></jsp:include>
	<br />
	<c:if test="${sessionScope.error ne null}">
		<div class="errorblock">
			<p>${error}</p>
		</div>
	</c:if>
	<c:out value="${user.userName}"></c:out>
	, estas siguiendo a:
	<ul>
		<c:forEach var="followed" items="${followedUsers}">
			<li>${followed.userName}</li>
		</c:forEach>
	</ul>
	<br /> Estas siguiendo a Tags:
	<ul>
		<c:forEach var="followed" items="${followedTags}">
			<li>${followed.tagName}</li>
		</c:forEach>
	</ul>
	Te estan siguiendo:
	<ul>
		<c:forEach var="follower" items="${followedBy}">
			<li>${follower.userName}</li>
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
						<td><a
							href="<c:out value='${user.userName}' ></c:out>/videos">${fn:length(user.videos)}</a></td>
						<td>${fn:length(user.followedUsers)}</td>
						<td>${fn:length(user.followedTags)}</td>
						<td>${fn:length(user.followedBy)}</td>
						<td>
							<form action="user/follow/${user.userName}" method="POST">
								<input type="submit" value="follow">
							</form>
						</td>
						<td>
							<form action="user/unfollow/${user.userName}" method="POST">
								<input type="submit" value="unfollow">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<%--
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
 --%>
</body>
</html>