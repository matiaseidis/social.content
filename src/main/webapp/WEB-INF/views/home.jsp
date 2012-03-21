<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<span>Lo que esta pasando</span>
	<br />
	-------------------------------------------------------------------------------------	
	
	<p>TODO</p>
	<p>submit content sin titulo boom</p>
	<p>eventos / calendario</p>
	<p>update content</p>
	<p>update user profile</p>
	<p>datos de perfil -> redes sociales???</p>
-------------------------------------------------------------------------------------	
	<myTags:contentList contentList="${user.followedVideos}" title="Videos" ></myTags:contentList>
	<myTags:contentList contentList="${user.followedPosts}" title="Posts" ></myTags:contentList>
	
	<myTags:lastVideos videoList="${lastVideos}" title="Videos publicados"></myTags:lastVideos>
	
</body>
</html>
