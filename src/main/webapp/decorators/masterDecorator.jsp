<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/fonts.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/layout.css" />
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-1.7.1.min.js"></script>

<!-- <script type="text/javascript" -->
<%-- 	src="${ctx}/js/jquery/jquery.easing.1.3.js"></script> --%>

<!-- <script type="text/javascript" -->
<%-- 	src="${ctx}/js/jquery/jquery.easing.compatibility.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/js/menu.js"></script> --%>

<title>Social Content :: <sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
</head>
<body>
	<div class="barrita-wrapper">
		<div class="barrita">
			<jsp:include page="/barrita.jsp"></jsp:include>
		</div>
	</div>
	<div class="page-wrapper">
		<br />
		<c:if test="${sessionScope.errors ne null}">
			<div class="errorblock">
				<p>${errors}</p>
			</div>
		</c:if>
		<div class='mainBody'>
			<h1 class='title'>
				<sitemesh:write property='title' />
			</h1>
			<sitemesh:write property='body' />
		</div>
		<div class='sidebar'>
			<!-- 			sidebar right -->
			<sec:authorize access="isAuthenticated()">
				<myTags:userImg height="100" width="100" username="${user.userName}"></myTags:userImg>
				<c:out value="${user.userName}"></c:out>

				<p></p>
				<c:if test="${not empty followedContent}">
				Contenido de gente a la que seguis
				<div>
						<c:forEach items="${followedContent}" var="c">
							<p>
								<a href="${ctx}/content/${c.contentType}/show/${c.id}"><c:out
										value="${c.title}"></c:out></a>
							</p>

							<p>
								<c:out value="${c.author.userName}"></c:out>
								-
								<fmt:formatDate pattern="dd" type="date" value="${c.postDate}"
									timeZone="es" />
								de
								<fmt:formatDate pattern="MM" type="date" value="${c.postDate}"
									timeZone="es" />
								de
								<fmt:formatDate pattern="yyyy, HH:mm" type="time"
									value="${c.postDate}" timeZone="es" />
							</p>
							
						</c:forEach>
					</div>
				</c:if>

				<myTags:userList title="followedUsers" userList="${followedUsers}">usuarios que estas siguiendo</myTags:userList>
				<myTags:userList title="followedBy" userList="${followedBy}">usuarios que te siguen</myTags:userList>
				<myTags:tagList title="followedTags" tagList="${followedTags}">etiquetas que estas siguiendo</myTags:tagList>
				<%-- 				<myTags:tagList title="tags" tagList="${tags}">etiquetas</myTags:tagList> --%>
			</sec:authorize>
		</div>

		<div class='disclaimer'>Site disclaimer. This is an example.</div>
	</div>
</body>
</html>