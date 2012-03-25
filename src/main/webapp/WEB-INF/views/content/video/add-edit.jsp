<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>
<jsp:include page="../content-form-title.jsp"></jsp:include>
</title>
<script type="text/javascript" src="${ctx}/js/app/tags.js"></script>

</head>
<body>

	<form:form action="${action}" modelAttribute="content" method="post"
		enctype="multipart/form-data">

		<jsp:include page="../id.jsp"></jsp:include>
		<jsp:include page="../title.jsp"></jsp:include>
		
<!-- 		TODO borrar este jsp si ya no va 
(puede ir en las vistas de contenido que no sean ni add ni edit)-->
<%-- 		<jsp:include page="../show-tags-box.jsp"></jsp:include> --%>
		<jsp:include page="../add-tag-box.jsp"></jsp:include>
		<jsp:include page="../description.jsp"></jsp:include>
<%-- 		<jsp:include page="../upload.jsp"></jsp:include> --%>

		<p>
			<form:label for="url" path="url">URL: <c:out
					value="${video.url}" />
			</form:label>
			<form:input path="url" type="text" class="medium fixed-height" />
			<myTags:errorField field="url"></myTags:errorField>
		</p>


		<jsp:include page="../submit.jsp"></jsp:include>

	</form:form>

	<%@ include file="/wysiwyg.html"%>
	<script>
		$(function() {
			$('#title').focus();
		});
	</script>
</body>
</html>