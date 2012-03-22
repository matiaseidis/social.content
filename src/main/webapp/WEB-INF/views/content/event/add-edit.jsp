<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<html>
<head>
<script type="text/javascript"
	src="${ctx}/js/jquery/ui/js/jquery-ui-1.8.18.custom.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery/ui/css/ui-lightness/jquery-ui-1.8.18.custom.css" />
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>
<jsp:include page="../content-form-title.jsp"></jsp:include>
</title>
</head>
<body>

	<form:form action="${action}" modelAttribute="content" method="post"
		enctype="multipart/form-data">

		<jsp:include page="../id.jsp"></jsp:include>
		<jsp:include page="../title.jsp"></jsp:include>
		
<!-- 		<p>Inicio: <input type="text" id="datepicker-start"></p> -->
<!-- 		<p>Fin: <input type="text" id="datepicker-end"></p> -->
		
		<form:label for="start" path="start">Comienza:</form:label>
		<form:input path="start" id="datepicker-start" class="fixed-height" />
		
		<br />
		
<%-- 		<form:label for="end" path="end">Finaliza:</form:label> --%>
<%-- 		<form:input path="end" id="datepicker-end" class="fixed-height" /> --%>

		<label for="end" >Finaliza:</label>
		<input id="datepicker-end" name="end" class="fixed-height" />
		<p type="text" id="alternate" />

		
		<jsp:include page="../show-tags-box.jsp"></jsp:include>
		<jsp:include page="../add-tag-box.jsp"></jsp:include>
		<jsp:include page="../description.jsp"></jsp:include>
<%-- 		<jsp:include page="../upload.jsp"></jsp:include> --%>

		<jsp:include page="../submit.jsp"></jsp:include>

	</form:form>

	<%@ include file="/wysiwyg.html"%>
	<script>
		$(function() {
			$('#title').focus();
			$("#datepicker-start").datepicker({
				altField: "#alternate"
			});
			$("#datepicker-end").datepicker();
		});
	</script>
	
</body>
</html>