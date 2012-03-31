<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<a href='<c:url value="/profile/${content.author.userName}"/>'> <myTags:userImg
		height="27" width="27" username="${content.author.userName}"  hasOwnImage="${user.hasOwnImage}"></myTags:userImg></a>
Publicado por 
<a href='<c:url value="/profile/${content.author.userName}"/>'>
	<c:out value="${content.author.userName}"></c:out></a>, el <myTags:shortDate date="${content.postDate}" ></myTags:shortDate><myTags:time date="${content.postDate}" title="a las"></myTags:time>
<myTags:followUnfollowUser followed="${content.author}" follower="${user}"></myTags:followUnfollowUser>
