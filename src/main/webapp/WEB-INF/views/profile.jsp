<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perfil de usuario</title>

</head>
<body>
	<div class="user-profile">
		<c:if test="${followed ne null}">
			<p>
				<c:choose>
					<c:when test="${followed}">
				Estas siguiendo a <c:out value="${profileUser.userName}"></c:out>

						<form action="${ctx}/user/unfollow/${profileUser.userName}"
							method="POST">
							<input type="submit" value="unfollow">
						</form>


					</c:when>
					<c:otherwise>
				No estas siguiendo a <c:out value="${profileUser.userName}"></c:out>

						<form action="${ctx}/user/follow/${profileUser.userName}"
							method="POST">
							<input type="submit" value="follow">
						</form>



					</c:otherwise>

				</c:choose>
			</p>
		</c:if>
		<p>
			<c:out value="${profileUser.userName}"></c:out>
		</p>
		<c:if test="${own}">
			<form action="${ctx}/user/profile/edit"
							method="GET">
							<input type="submit" value="editar perfil">
						</form>
		</c:if>
		<myTags:userImg height="200" width="200"
			username="${profileUser.userName}"></myTags:userImg>
		<p>
			<c:out value="${profileUser.info}"></c:out>
		</p>
		

		<p>Siguiendo a ${fn:length(profileUser.followedUsers)} usuario(s)</p>
		<p>Lo estan siguiendo ${fn:length(profileUser.followedBy)}
			usuario(s)</p>
		<p>Siguiendo a ${fn:length(profileUser.followedTags)} etiqueta(s)</p>

	</div>
	
	<div>
	<p>Contenido publicado:</p>
		<myTags:contentList contentList="${profileUser.videos}" title="Videos"></myTags:contentList>
		<myTags:contentList contentList="${profileUser.posts}" title="Posts"></myTags:contentList>
	
	</div>


</body>
</html>