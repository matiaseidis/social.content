<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="page" required="true" rtexprvalue="true"%>
<%@ attribute name="updatedTagId" required="true" rtexprvalue="true"%>
<%@ attribute name="userList" required="true" rtexprvalue="true"
	type="java.util.List"%>

<%@ tag body-content="tagdependent"%>
<c:if test="${not empty userList}">
	<div>
			<h3>${title}</h3>

			<ul>
				<c:forEach var="u" items="${userList}">
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
		<div class="pager">
			<span><a class="previous" href="${prevPage}">
			<c:if test="${prevPage gt -1}">
			<c:out value="<<"></c:out>
			</c:if>
			</a></span>
			<span><a class="next" href="${nextPage}">
			<c:if test="${nextPage gt 0}">
			<c:out value=">>"></c:out>
			</c:if>
			</a></span>
		</div>
	
</c:if>
<script>
$(function(){
	$('#${updatedTagId} .previous').click(function(e){
		e.preventDefault();
// 		alert(44);
		var prevPage = ${prevPage};
		if(prevPage == -1){
			return;
		}
		$.ajax({
	        url: '${ctx}/ajax/${updatedTagId}/'+prevPage+'/'+3,
// 	        data: ({name : "me"}),
	        success: function(data) {
	          
	        	$('#${updatedTagId}').html(data);
	        },
	        error: function(data) {
		          alert(data);
		        	$('#${updatedTagId}').html(data);
		        }
	      });
	});
	$('#${updatedTagId} .next').click(function(e){
		e.preventDefault();
		var nextPage = ${nextPage};
		if(nextPage == 0){
			return;
		}
		var nextPage = $('#next').attr('href');
		$.ajax({
	        url: '${ctx}/ajax/${updatedTagId}/'+nextPage+'/'+3,
// 	        data: ({name : "me"}),
	        success: function(data) {
	          
	        	$('#${updatedTagId}').html(data);
	        },
	        error: function(data) {
		          alert(data);
		        	$('#${updatedTagId}').html(data);
		        }
	      });
	});
});
</script>