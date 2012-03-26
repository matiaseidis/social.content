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
	</c:if>
</sec:authorize>

