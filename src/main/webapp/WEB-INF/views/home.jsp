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
<p>
 </p>
	<span>Lo que esta pasando</span>
	<br />
	-------------------------------------------------------------------------------------

	<p>TODO</p>
	<p>en la vista de view agregar un cubo con info de tags:
	<pre>
	tagged
javascript × 193601
jquery × 167367
html × 87025
forms × 16626
webkit × 2653

y del contenido:

asked 3 years ago

viewed 72,128 times

active 11 months ago
	</pre></p>
	<p>update user password</p>
	<p>datos de perfil -> agregar redes sociales (?)</p>
	<p>poder borrar usuario</p>
	<p>el evento debe ser suscribible</p>
	<p>no se puede borrar contenido en la vista de edit (o mandar el link de delete a user/content)!!!!</p>
	<p>interfaz de admin?</p>
	-------------------------------------------------------------------------------------

	<%-- 	<myTags:paginatedContentList contentList="${followedContent}" title="paged content" page="1"></myTags:paginatedContentList> --%>
	<sec:authorize access="isAuthenticated()">
<!-- 	<div id="paginatedContent"></div> -->


<%-- 	<myTags:contentList contentList="${user.followedVideos}" title="Videos"></myTags:contentList> --%>
<%-- 	<myTags:contentList contentList="${user.followedPosts}" title="Posts"></myTags:contentList> --%>

</sec:authorize>
<%-- 	<myTags:lastVideos videoList="${lastVideos}" title="Videos publicados"></myTags:lastVideos> --%>
</body>
</html>
