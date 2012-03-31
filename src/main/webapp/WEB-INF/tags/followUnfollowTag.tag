<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="user" required="true" rtexprvalue="true"
	type="com.mati.demo.model.user.User"%>
<%@ attribute name="tag" required="true" rtexprvalue="true"
	type="com.mati.demo.model.tag.Tag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<sec:authorize access="isAuthenticated()">
<c:choose>
				<c:when test="${myFunctions:isTagFollowedBy(tag, user)}">
								<c:set var="text" value="dejar de seguir '${tag.tagName}'"/>
								<c:set var="img" value="minus"/>
							</c:when>
				<c:otherwise>
							<c:set var="text" value="comenzar a seguir '${tag.tagName}'"/>
							<c:set var="img" value="add"/>
							</c:otherwise>
			</c:choose>
	<div class="follow-tag-link follow-link">
		<a href="#" class="follow-unfollow-tag" name="${tag.tagName}" title="${text}" 
			onclick='followUnfollow(event, "<c:out value="${tag.tagName}"></c:out>", "tag")'>
			<%-- 			<img src='<c:url value="/img/${img}.gif" />' /> --%>
			<img width="8px" src='<c:url value="/img/symbolize-icons-set/png/16x16/${img}.png" />' />
			
		</a>
	</div>
</sec:authorize>
