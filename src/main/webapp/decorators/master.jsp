<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<style type='text/css'>
/* Some CSS */
body {
	font-family: arial, sans-serif;
	background-color: #ffffcc;
}

h1,h2,h3,h4 {
	text-align: center;
	background-color: #ccffcc;
	border-top: 1px solid #66ff66;
}

.mainBody {
	padding: 10px;
	border: 1px solid #555555;
}

.disclaimer {
	text-align: center;
	border-top: 1px solid #cccccc;
	margin-top: 40px;
	color: #666666;
	font-size: smaller;
}
</style>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<sitemesh:write property='head' />
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
	<h1 class='title'>
		<sitemesh:write property='title' />
	</h1>

	<div class='mainBody'>
		<sitemesh:write property='body' />
	</div>
	<div class='sideBar'>
	sidebar right
		<sec:authorize access="isAuthenticated()">
			<myTags:userList title="seg" userList="${followedUsers}">msg</myTags:userList>
			<myTags:userList title="te s" userList="${followedBy}">msg</myTags:userList>
			<myTags:tagList title="tot tags" tags="${tags}">msg tags</myTags:tagList>
			<myTags:tagList title="seg tags" tags="${followedTags}">msg??</myTags:tagList>
		</sec:authorize>
	</div>

	<div class='disclaimer'>Site disclaimer. This is an example.</div>

</body>
</html>