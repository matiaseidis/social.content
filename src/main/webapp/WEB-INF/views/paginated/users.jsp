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
		<h3>${title} (${total} de ${fullTotal})</h3>
		<ul>
			<c:forEach var="u" items="${followedList}">
				<li class="${u.userName}"><a
					href="${ctx}/profile/${u.userName}"> <myTags:userImg
							height="${imgSize}" width="${imgSize}" username="${u.userName}"
							hasOwnImage="${u.hasOwnImage}"></myTags:userImg></a> <a
					href="${ctx}/profile/${u.userName}">${u.userName}</a> 
					<myTags:followUnfollowUser
 						followed="${u}" follower="${user}"></myTags:followUnfollowUser> 
						</li>
			</c:forEach>
		</ul>
	</div>
	<myTags:pager nextPage="${nextPage}" prevPage="${prevPage}"
		total="${total}" updatedTagId="${updatedTagId}"></myTags:pager>
</c:if>