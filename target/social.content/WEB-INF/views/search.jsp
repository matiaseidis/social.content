<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buscar</title>
</head>
<body>
	<div>
		<span>Buscar contenidos</span>
		<form action="${ctx}/search/content/" method="POST">
			<input type="text" name="pattern"> <input type="submit"
				value="buscar">
		</form>
		<myTags:contentList contentList="${contentSearchResult}"
			title="Resultados"></myTags:contentList>
	</div>

	<div>
		<span>Buscar usuarios</span>
		<form action="${ctx}/search/users/" method="POST">
			<input type="text" name="pattern"> <input type="submit"
				value="buscar">
		</form>
		<myTags:userList userList="${usersSearchResult}" title="Resultados"></myTags:userList>
	</div>

	<div>
		<span>Buscar etiquetas</span>
		<form action="${ctx}/search/tags/" method="POST">
			<input type="text" name="pattern"> <input type="submit"
				value="buscar">
		</form>
		<myTags:tagList tagList="${tagsSearchResult}" title="Resultados"></myTags:tagList>
	</div>
</body>
</html>