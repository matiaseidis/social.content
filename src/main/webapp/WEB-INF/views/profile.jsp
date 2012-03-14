<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perfil de usuario</title>

</head>
<body>
	<div class="user-profile">
		<myTags:userImg height="300" width="300"
			username="${profileUser.userName}"></myTags:userImg>
		<p>
			<c:out value="${profileUser.userName}"></c:out>
		</p>
		<p>
			<c:out value="mail"></c:out>
		</p>
		<p>
			<c:out value="algo mas"></c:out>
		</p>
		<p>
			<c:out value="del usuario"></c:out>
		</p>

		<p>Siguiendo a ${fn:length(profileUser.followedUsers)} usuarios</p>
		<p>Lo estan siguiendo ${fn:length(profileUser.followedBy)}
			usuarios</p>
		<p>Siguiendo a ${fn:length(profileUser.followedTags)} etiquetas</p>

	</div>


</body>
</html>