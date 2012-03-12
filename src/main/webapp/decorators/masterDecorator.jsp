<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/layout.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.1.min.js" ></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easing.1.3.js" ></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easing.compatibility.js" ></script>
	<script>
    
    $(document).ready(function(){  
        
        //When mouse rolls over  
        $("li.active").mouseover(function(){  
            $(this).stop().animate({height:'150px'},{queue:false, duration:600, easing: 'easeOutBounce'});
        });  
      
        //When mouse is removed  
        $("li.active").mouseout(function(){  
            $(this).stop().animate({height:'50px'},{queue:false, duration:600, easing: 'easeOutBounce'});  
        });  
      
    });  
    
	</script> 
<title><sitemesh:write property='title' /></title>
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
				<c:out value="${user.userName}"></c:out>
				<img src="${userPictureURI}" width="100px" height="100px"/>
				<myTags:userList title="followedUsers" userList="${followedUsers}">usuarios que estas siguiendo</myTags:userList>
				<myTags:userList title="followedBy" userList="${followedBy}">usuarios que te siguen</myTags:userList>
				<myTags:tagList title="followedTags" tags="${followedTags}">etiquetas que estas siguiendo</myTags:tagList>
				<myTags:tagList title="tags" tags="${tags}">etiquetas</myTags:tagList>
			</sec:authorize>
		</div>

		<div class='disclaimer'>Site disclaimer. This is an example.</div>
	</div>
</body>
</html>