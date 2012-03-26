<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="user" required="true" rtexprvalue="true" type="com.mati.demo.model.user.User"%>
<%@ attribute name="tag" required="true" rtexprvalue="true" type="com.mati.demo.model.tag.Tag"%>

<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<c:choose>
		<c:when test="${myFunctions:isTagFollowedBy(tag, user)}">
			<form class="follow"
				action='<c:url value="/tag/unfollow/${tag.tagName}" />'
				method="POST">
				<input class="button" type="submit" value="no seguir">
			</form>
		</c:when>
		<c:otherwise>
			<form class="follow"
				action='<c:url value="/tag/follow/${tag.tagName}" />' method="POST">
				<input class="button" type="submit" value="seguir">
			</form>
		</c:otherwise>
	</c:choose>
</sec:authorize>
