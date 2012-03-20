<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><jsp:include page="../content-form-title.jsp"></jsp:include>
</title>
</head>
<body>

	<form:form action="${action}" modelAttribute="content" method="post">
		<%-- 	<form action="<c:out value="${action}"></c:out>" method=POST> --%>
		<jsp:include page="../title.jsp"></jsp:include>
		<jsp:include page="../show-tags-box.jsp"></jsp:include>
		<jsp:include page="../add-tag-box.jsp"></jsp:include>
		<p>Body</p>
		<textarea rows="20" cols="70" name="body">
			<c:choose>
				<c:when test="${not empty errors}">
					<c:out value="${errors.post.body}"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="${post.body}"></c:out>
				</c:otherwise>
			</c:choose>
		</textarea>
		<jsp:include page="../submit.jsp"></jsp:include>
	</form:form>
	<%-- 	</form> --%>


	<%@ include file="/wysiwyg.html"%>
	<script>
		$(function() {
			$('#title').focus();
		});
	</script>
</body>
</html>