<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title><jsp:include page="../content-form-title.jsp"></jsp:include></title>
</head>
<body>
	<form:form action="${action}" modelAttribute="content" method="post"
		enctype="multipart/form-data">

		<jsp:include page="../id.jsp"></jsp:include>
		<jsp:include page="../title.jsp"></jsp:include>
		<jsp:include page="../show-tags-box.jsp"></jsp:include>
		<jsp:include page="../add-tag-box.jsp"></jsp:include>
		<jsp:include page="../upload.jsp"></jsp:include>
		<jsp:include page="../description.jsp"></jsp:include>
		<jsp:include page="../submit.jsp"></jsp:include>
		
	</form:form>
	<%@ include file="/wysiwyg.html"%>
	<script>
	$(function(){$('#title').focus();});
	</script>
</body>
</html>
