<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${content.title}"></c:out></title>
</head>
<body>
<jsp:include page="../content-id.jsp"></jsp:include>
	<jsp:include page="../edit-link.jsp"></jsp:include>
	<jsp:include page="../delete-link.jsp"></jsp:include>
	
	<c:choose>
	<c:when test="${content.end ne null}">
	<c:set var="dateTitle" value="Desde"></c:set>
	</c:when>
	<c:otherwise>
	<c:set var="dateTitle" value="El"></c:set>
	</c:otherwise>
	</c:choose>	
	<myTags:shortDate date="${content.start}" title="${dateTitle}"></myTags:shortDate>
	<myTags:shortDate date="${content.end}" title="Hasta"></myTags:shortDate>

	<jsp:include page="../show-tags-box.jsp"></jsp:include>

	<div class="content-description">
		<p>
			<c:out value="${content.body}" escapeXml="false"></c:out>
		</p>
	</div>
	
	<div id="relation-box-wrapper">
		<jsp:include page="../relations-box.jsp" />
	</div>
	<jsp:include page="../rating-box.jsp" />
	<jsp:include page="../comments-box.jsp" />
</body>
</html>