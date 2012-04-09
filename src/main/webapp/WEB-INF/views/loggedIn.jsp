<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<div class="menu-left">
		<a href="${ctx}/" title="Inicio">
		<img src="<c:url value='/img/symbolize-icons-set/png/32x32/home.png' />" /></a>
		<a href="${ctx}/user/content" title="Mis cosas">
		<img src="<c:url value='/img/symbolize-icons-set/png/16x16/briefcase.png' />" /></a> 
		<a href="${ctx}/user/content/add" title="Agregar contenido">
		<img src="<c:url value='/img/symbolize-icons-set/png/16x16/page.png' />" /></a>
<%-- 		<a href="${ctx}/search" title="Buscar"> --%>
<%-- 		<img src="<c:url value='/img/symbolize-icons-set/png/32x32/search.png' />" /></a> --%>
</div>
<div class="menu-right">
<!-- 	<form action="search" class="search-form"> -->
<!-- 	<input type="text" name="text"> -->
<!-- 	<input type="submit" value="Buscar"> -->
<!-- 	</form> -->
	<a href="${ctx}/profile/${user.userName}" title="Perfil">
<%-- 	<img src="<c:url value='/img/symbolize-icons-set/png/32x32/user.png' />" /> --%>
	<c:out value="${user.userName}"></c:out>
	<myTags:userImg height="27" width="27" username="${user.userName}" hasOwnImage="${user.hasOwnImage}"></myTags:userImg>
	</a> 
	<a href="${ctx}/j_spring_security_logout" title="Logout">
	<img src="<c:url value='/img/symbolize-icons-set/png/16x16/delete.png' />" /></a>
</div>

<!-- <ul> -->
<!-- 	<li class="green"> -->
<!-- 		<p> -->
<%-- 			<a href="${pageContext.request.contextPath}/">Inicio</a> --%>
<!-- 		</p> -->
<!-- 		<p class="subtext">The front page</p> -->
<!-- 	</li> -->
<!-- 	<li class="yellow"> -->
<!-- 		<p> -->
<%-- 			<a href="${pageContext.request.contextPath}/profile">Perfil</a> --%>
<!-- 		</p> -->
<!-- 		<p class="subtext">More info</p> -->
<!-- 	</li> -->
<!-- 	<li class="red"> -->
<!-- 		<p> -->
<%-- 			<a href="${pageContext.request.contextPath}/user/content">Mis --%>
<!-- 				cosas</a> -->
<!-- 		</p> -->
<!-- 		<p class="subtext">Get in touch</p> -->
<!-- 	</li> -->
<!-- 	<li class="blue active"> -->
<!-- 		<p>Agregar contenido</p> -->
<!-- 		<p class="subtext"> -->
<%-- 			<a href="${pageContext.request.contextPath}/content/post/add">Crear --%>
<!-- 				Post</a> -->
<!-- 		</p> -->
<!-- 		<p class="subtext"> -->
<%-- 			<a href="${pageContext.request.contextPath}/content/video/add">Crear --%>
<!-- 				Video</a> -->
<!-- 		</p> -->
<!-- 	</li> -->
<!-- 	<li class="purple"> -->
<!-- 		<p> -->
<%-- 			<a href="${pageContext.request.contextPath}/relations">Relaciones</a> --%>
<!-- 		</p> -->
<!-- 		<p class="subtext">Legal things</p> -->
<!-- 	</li> -->
<!-- 	<li class="green"> -->
<!-- 		<p> -->
<%-- 			<a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a> --%>
<!-- 		</p> -->
<!-- 		<p class="subtext">Legal things</p> -->
<!-- 	</li> -->
<!-- </ul> -->
<!-- <style> -->
<!-- /* /*         body{   */ */ -->
<!-- /* /*         font-family:"Lucida Grande", arial, sans-serif;   */ */ -->
<!-- /* /*         background:#F3F3F3;   */ */ -->
<!-- /* /*     }   */ */ -->
<!-- /* .barrita ul { */ -->
<!-- /* 	margin: 0; */ -->
<!-- /* 	padding: 0; */ -->
<!-- /* } */ -->

<!-- /* .barrita li { */ -->
<!-- /* 	width: 150px; */ -->
<!-- /* 	height: 50px; */ -->
<!-- /* 	float: left; */ -->
<!-- /* 	color: #191919; */ -->
<!-- /* 	text-align: center; */ -->
<!-- /* 	overflow: hidden; */ -->
<!-- /* } */ -->

<!-- /* .barrita p,.barrita a { */ -->
<!-- /* 	color: #FFF; */ -->
<!-- /* 	text-decoration: none; */ -->
<!-- /* } */ -->

<!-- /* .barrita p { */ -->
<!-- /* 	padding: 0px 5px; */ -->
<!-- /* } */ -->

<!-- /* .barrita .subtext { */ -->
<!-- /* 	padding-top: 15px; */ -->
<!-- /* } */ -->

<!-- /* /*Menu Color Classes*/ */ -->
<!-- /* .barrita .green { */ -->
<!-- /* 	background: #6AA63B; */ -->
<!-- /* } */ -->

<!-- /* .barrita .yellow { */ -->
<!-- /* 	background: #FBC700; */ -->
<!-- /* } */ -->

<!-- /* .barrita .red { */ -->
<!-- /* 	background: #D52100; */ -->
<!-- /* } */ -->

<!-- /* .barrita .purple { */ -->
<!-- /* 	background: #5122B4; */ -->
<!-- /* } */ -->

<!-- /* .barrita .blue { */ -->
<!-- /* 	background: #0292C0; */ -->
<!-- /* } */ -->
<!-- </style> -->
