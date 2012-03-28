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
				<li class="${u.userName}"><a href="${ctx}/profile/${u.userName}"> <myTags:userImg
							height="${imgSize}" width="${imgSize}" username="${u.userName}"></myTags:userImg></a>

					<a href="${ctx}/profile/${u.userName}">${u.userName}</a> 
						<c:choose >
							<c:when test="${updatedTagId eq 'followedBy'}">
								<c:if test="${myFunctions:isUserFollowedBy(u, user) ne true}">
									<a href="#" class="follow-user" name="${u.userName}" 
									onclick='process("<c:out value="${u.userName}"></c:out>", "follow", "${updatedTagId}", ${page}, ${total})'>seguir</a>
								</c:if>
							</c:when>
							<c:otherwise>
								<a href="#" class="unfollow-user" name="${u.userName}" 
								onclick='process("<c:out value="${u.userName}"></c:out>", "unfollow", "${updatedTagId}", ${page}, ${total})'>no seguir</a>
							</c:otherwise>
						</c:choose>
<%-- 						<c:choose> --%>
<%-- 						<c:when test="${myFunctions:isUserFollowedBy(u, user)}"> --%>
<%-- 							<a href="#" class="unfollow-user" name="${u.userName}" onclick='process("<c:out value="${u.userName}"></c:out>", "unfollow", "${updatedTagId}", ${page}, ${total})'>no seguir</a> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 							<a href="#" class="follow-user" name="${u.userName}" onclick='process("<c:out value="${u.userName}"></c:out>", "follow", "${updatedTagId}", ${page}, ${total})'>seguir</a> --%>
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
					</li>
			</c:forEach>
		</ul>
	</div>
	<myTags:pager nextPage="${nextPage}" prevPage="${prevPage}" total="${total}"
		updatedTagId="${updatedTagId}"></myTags:pager>

	<script>
		var followedBy = 'followedBy';
		var followedUsers = 'followedUsers';

		var process = function(name, action, id, page, total){
// 		event.preventDefault();
			$.ajax({
				type: "POST",
		        url: '${ctx}/ajax/'+action+'/user/'+name,
		        data: {
		        	refresh:id,
		        	page:page,
		        	total:total
		        },
		        success: function(data) {
		        	$('#'+id).html(data);
					var action = (id  == followedBy) ? followedUsers : followedBy;
					paginate(action);
		        },
		        error: function(data) {
			          alert(data);
			        	$('#'+id).html(data);
			        }
		      });
		};
	</script>


</c:if>