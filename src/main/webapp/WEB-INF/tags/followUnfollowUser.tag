<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="follower" required="true" rtexprvalue="true"
	type="com.mati.demo.model.user.User"%>
<%@ attribute name="followed" required="true" rtexprvalue="true"
	type="com.mati.demo.model.user.User"%>

<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<c:if test="${follower ne followed}">

		<c:choose>
			<c:when test="${myFunctions:isUserFollowedBy(followed, follower)}">
				<form id="user-unfollow" class="follow"
					action='<c:url value="/user/unfollow/${user.userName}" />'
					method="POST">
					<input class="button" type="submit" value="no seguir">
				</form>
			</c:when>
			<c:otherwise>
				<form id="user-follow" class="follow"
					action='<c:url value="/user/follow/${user.userName}" />'
					method="POST">
					<input class="button" type="submit" value="seguir">
				</form>
			</c:otherwise>
		</c:choose>
	</c:if>
</sec:authorize>
