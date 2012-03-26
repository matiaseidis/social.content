<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:if test="${not empty followedList}">
	<div>
		<h3>${title}</h3>
		<ul>
			<c:forEach var="u" items="${followedList}">
				<li><a href="${ctx}/profile/${u.userName}"> <myTags:userImg
							height="50" width="50" username="${u.userName}"></myTags:userImg></a>

					<a href="${ctx}/profile/${u.userName}">${u.userName}</a> <c:choose>
						<c:when test="${myFunctions:isUserFollowedBy(u, user)}">
							<form class="follow" action="${ctx}/user/unfollow/${u.userName}"
								method="POST">
								<input class="button" type="submit" value="no seguir">
							</form>
						</c:when>
						<c:otherwise>
							<form class="follow" action="${ctx}/user/follow/${u.userName}"
								method="POST">
								<input class="button" type="submit" value="seguir">
							</form>
						</c:otherwise>
					</c:choose></li>
			</c:forEach>
		</ul>
	</div>
	<myTags:pager nextPage="${nextPage}" prevPage="${prevPage}"
		updatedTagId="${updatedTagId}"></myTags:pager>
</c:if>