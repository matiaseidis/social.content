<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<myTags:contentList contentList="${user.followedVideos}" title="Videos" ></myTags:contentList>
	<myTags:contentList contentList="${user.followedPosts}" title="Posts" ></myTags:contentList>
	


	<%-- <jsp:include page="/barrita.jsp"></jsp:include> --%>


	<!-- 	<h2>Social Content</h2> -->
	HOME trd
	<!-- 			sidebar right -->
	<sec:authorize access="isAuthenticated()">
		<c:out value="${user.userName}"></c:out>
		<myTags:userImg height="100" width="100" username="${user.userName}"></myTags:userImg>

		<p></p>
		<c:if test="${not empty followedContent}">
				Contenido de gente a la que seguis
				<div>
				<c:forEach items="${followedContent}" var="c">
					<p>
						<a href="${ctx}/content/${c.contentType}/show/${c.id}"><c:out
								value="${c.title}"></c:out></a>
					</p>

					<p>
						<c:out value="${c.author.userName}"></c:out>
						-
						<fmt:formatDate pattern="dd" type="date" value="${c.postDate}"
							timeZone="es" />
						de
						<fmt:formatDate pattern="MM" type="date" value="${c.postDate}"
							timeZone="es" />
						de
						<fmt:formatDate pattern="yyyy, HH:mm" type="time"
							value="${c.postDate}" timeZone="es" />
					</p>
					</p>
				</c:forEach>
			</div>
		</c:if>
	</sec:authorize>
</body>
</html>
