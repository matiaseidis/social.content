<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="userList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ tag body-content="tagdependent"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<sec:authorize access="isAuthenticated()">
	<c:if test="${not empty userList}">
		<div>
			<h3>${title} (${fn:length(userList)})</h3>

			<ul>
				<c:forEach var="u" items="${userList}">
					<li><a href="${ctx}/profile/${u.userName}"> <myTags:userImg
								height="50" width="50" username="${u.userName}" hasOwnImage="${u.hasOwnImage}"></myTags:userImg></a>

						<a href="${ctx}/profile/${u.userName}">${u.userName}</a> 
						<myTags:followUnfollowUser followed="${u}" follower="${user}"></myTags:followUnfollowUser>
						</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
</sec:authorize>

