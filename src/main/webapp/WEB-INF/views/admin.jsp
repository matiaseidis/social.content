<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
						<td><a href="${ctx}/user/${user.userName}/content">${user.userName}</a></td>
						<td>${user.password}</td>
						<td>${fn:length(user.roles)}</td>
						<td>${fn:length(user.posts)}</td>
						<td><a
							href="${ctx}/<c:out value='${user.userName}' ></c:out>/videos">${fn:length(user.videos)}</a></td>
						<td>${fn:length(user.followedUsers)}</td>
						<td>${fn:length(user.followedTags)}</td>
						<td>${fn:length(user.followedBy)}</td>
						<td>
							<form action="${ctx}/user/follow/${user.userName}" method="POST">
								<input class="button" type="submit" value="seguir">
							</form>
						</td>
						<td>
							<form action="${ctx}/user/unfollow/${user.userName}" method="POST">
								<input class="button" type="submit" value="no seguir">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<p>Tags (${fn:length(tags)}):</p>
	<div>
		<table>
			<thead>
				<tr>
					<td>name</td>
					<td>followed by</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tags}" var="tag">
					<tr>
						<td>${tag.tagName}</td>
						<td>${fn:length(tag.taggedUsers)}</td>
						<td>
							<form action="${ctx}/tag/follow/${tag.tagName}" method="POST">
								<input class="button" type="submit" value="seguir">
							</form>
						</td>
						<td>
							<form action="${ctx}/tag/unfollow/${tag.tagName}" method="POST">
								<input class="button" type="submit" value="no seguir">
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