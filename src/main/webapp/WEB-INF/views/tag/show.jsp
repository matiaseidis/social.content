<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>Tagged content</p>
<c:forEach items="${tag.taggedContent}" var="taggedContent">
	<a href="${ctx}/content/${taggedContent.contentType}/show/${taggedContent.id}">
	<span>${taggedContent.title}</span>
	</a>
	<span>${taggedContent.author.userName}</span>
	<br />
</c:forEach>
<br />
<p>Tagged users</p>
<c:forEach items="${tag.taggedUsers}" var="taggedUser">
<a href="${ctx}/profile/${taggedUser.userName}">
<span>${taggedUser.userName}</span>
</a>
<br />
</c:forEach>
<br />
<p>Tag followers</p>
<c:forEach items="${tag.followers}" var="follower">
<a href="${ctx}/profile/${follower.userName}">
<span>${follower.userName}</span>
</a>
<br />
</c:forEach>



</body>
</html>