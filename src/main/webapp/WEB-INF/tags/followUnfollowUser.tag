<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="follower" required="true" rtexprvalue="true" type="com.mati.demo.model.user.User"%>
<%@ attribute name="followed" required="true" rtexprvalue="true" type="com.mati.demo.model.user.User"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<div class="follow-user-link follow-link">
		<c:choose>
			<c:when test="${myFunctions:isUserFollowedBy(followed, follower)}">
				<c:set var="text" value="dejar de seguir '${followed.userName}'"/>
				<c:set var="img" value="minus"/>
			</c:when>
			<c:otherwise>
				<c:set var="text" value="comenzar a seguir '${followed.userName}'"/>
				<c:set var="img" value="add"/>
			</c:otherwise>
		</c:choose>
		<a href="#" class="follow-unfollow-user" title="${text}"  
			onclick='followUnfollow(event, "<c:out value="${followed.userName}"></c:out>", "user")'>
<%-- 			<img src='<c:url value="/img/${img}.gif" />' /> --%>
<%-- 			<img src='<c:url value="/img/handy/16x16/user.png" />' /> --%>
<%-- 			<img src='<c:url value="/img/handy/16x16/${img}.png" />' /> --%>
<img width="8px" src='<c:url value="/img/symbolize-icons-set/png/16x16/${img}.png" />' />			
			
		</a>
	</div>
</sec:authorize>
