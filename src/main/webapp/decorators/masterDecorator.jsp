<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/reset.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/fonts.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/layout.css' />" />
<script type="text/javascript"
	src="<c:url value='/js/jquery/jquery-1.7.1.min.js' />"></script>
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
		<c:if test="${message ne null}">
			<div class="message">
				<p>${message}</p>
			</div>
		</c:if>

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
				<div id="followedContent"></div>
				<div id="followedUsers"></div>
				<div id="followedBy"></div>
				<div id="followedEvents"></div>

				<%-- 				<myTags:userList title="followedUsers" userList="${followedUsers}">usuarios que estas siguiendo</myTags:userList> --%>
				<%-- 				<myTags:userList title="followedBy" userList="${followedBy}">usuarios que te siguen</myTags:userList> --%>
				<%-- 				<myTags:tagList title="followedTags" tagList="${followedTags}">etiquetas que estas siguiendo</myTags:tagList> --%>
				<%-- 				<myTags:tagList title="tags" tagList="${tags}">etiquetas</myTags:tagList> --%>
				<script>
					$(function() {
						var paginations = [ "followedContent", "followedUsers",
								"followedBy", "followedEvents" ];
						var paginate = function(value) {
							var elementId = "#" + value;
							$.ajax({
								url : '${ctx}/ajax/' + value + '/0/100',
								success : function(data) {
									$(elementId).html(data);
								},
								error : function(data) {
									alert(data);
									$(elementId).html(data);
								}
							});
						};

						$.each(paginations, function(index, value) {
							paginate(value);
						});
					});
				</script>
			</sec:authorize>
		</div>

		<div class='disclaimer'>Site disclaimer. This is an example.</div>
	</div>
</body>
</html>