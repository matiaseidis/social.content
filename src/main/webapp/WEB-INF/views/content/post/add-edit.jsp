<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><jsp:include page="../content-form-title.jsp"></jsp:include>
</title>
<script type="text/javascript" src="${ctx}/js/app/tags.js"></script>

</head>
<body>

	<form:form action="${action}" modelAttribute="content" method="post">
		
		<jsp:include page="../id.jsp"></jsp:include>
		<jsp:include page="../title.jsp"></jsp:include>
	<!-- 		TODO borrar este jsp si ya no va -->
<%-- 		<jsp:include page="../show-tags-box.jsp"></jsp:include> --%>
		<jsp:include page="../add-tag-box.jsp"></jsp:include>
		<jsp:include page="../body.jsp"></jsp:include>
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