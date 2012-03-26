<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<a href='<c:url value="/profile/${content.author.userName}"/>'> <myTags:userImg
		height="50" width="50" username="${content.author.userName}"></myTags:userImg></a>
Publicado por 
<a href='<c:url value="/profile/${content.author.userName}"/>'>
	<c:out value="${content.author.userName}"></c:out></a>, el <myTags:shortDate date="${content.postDate}" ></myTags:shortDate><myTags:time date="${content.postDate}" title="a las"></myTags:time>
<sec:authorize access="isAuthenticated()">
	<c:choose>
		<c:when test="${myFunctions:isUserFollowedBy(content.author, user)}">
			<form class="follow" action='<c:url value="/user/unfollow/${content.author.userName}" />' 
				method="POST">
				<input class="button" type="submit" value="no seguir">
			</form>
		</c:when>
		<c:otherwise>
			<form class="follow" action='<c:url value="/user/follow/${content.author.userName}" />'
				method="POST">
				<input class="button" type="submit" value="seguir">
			</form>
		</c:otherwise>
	</c:choose>
</sec:authorize>
