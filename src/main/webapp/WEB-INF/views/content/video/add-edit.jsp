<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>
<jsp:include page="../content-form-title.jsp"></jsp:include>
</title>
</head>
<body>

	<form:form action="${action}" modelAttribute="content" method="post"
		enctype="multipart/form-data">

		<jsp:include page="../title.jsp"></jsp:include>
		<jsp:include page="../show-tags-box.jsp"></jsp:include>
		<jsp:include page="../add-tag-box.jsp"></jsp:include>
		<jsp:include page="../description.jsp"></jsp:include>
<%-- 		<jsp:include page="../upload.jsp"></jsp:include> --%>

		<p>
			<form:label for="url" path="url">URL: <c:out
					value="${video.url}" />
			</form:label>
			<form:input path="url" type="text" class="medium fixed-height" />

		</p>


		<jsp:include page="../submit.jsp"></jsp:include>

	</form:form>

<%-- 	<form:form action="${action}" modelAttribute="video" method="post" --%>
<%-- 		enctype="multipart/form-data"> --%>

<!-- 		<p> -->
<%-- 			<form:label for="title" path="title">Título</form:label> --%>
<!-- 			<br /> -->
<%-- 			<form:input path="title" class="long" /> --%>
<%-- 			<c:if test="${not empty errors}"> --%>
<%-- 				<c:out value="${errors.title}"></c:out> --%>
<%-- 			</c:if> --%>
<!-- 		</p> -->
<%-- 		<jsp:include page="../show-tags-box.jsp"></jsp:include> --%>
<%-- 		<jsp:include page="../add-tag-box.jsp"></jsp:include> --%>
<!-- 		<input type="hidden" name="plainTags" id="contentTags" /> -->
<!-- 		<p> -->
<!-- 		<h3>Descripcion</h3> -->
<!-- 		</p> -->

<!-- 		<textarea class="corta" rows="2" cols="10" name="body"> -->
<%-- <c:choose> --%>
<%-- 	<c:when test="${not empty errors}"> --%>
<%-- 					<c:out value="${errors.post.body}"></c:out> --%>
<%-- 				</c:when> --%>
<%-- 	<c:otherwise> --%>
<%-- 					<c:out value="${post.body}"></c:out> --%>
<%-- 				</c:otherwise> --%>
<%-- </c:choose> --%>
<!-- </textarea> -->
<!-- 		<p> -->
<%-- 			<form:label for="fileData" path="fileData">Archivo: <c:out --%>
<%-- 					value="${video.fileName}" /> --%>
<%-- 			</form:label> --%>

<%-- 			<form:input path="fileData" type="file" /> --%>

<!-- 		</p> -->
<!-- 		<p> -->
<%-- 			<form:label for="url" path="url">URL: <c:out --%>
<%-- 					value="${video.url}" /> --%>
<%-- 			</form:label> --%>
<!-- 			<br /> -->
<%-- 			<form:input path="url" type="text" class="long" /> --%>

<!-- 		</p> -->

<!-- 		<p> -->
<!-- 			<input type="submit" class="button" -->
<%-- 				value="<c:choose><c:when test="${new eq null}">Actualizar</c:when><c:otherwise>Crear</c:otherwise></c:choose> ${entityName}" /> --%>
<!-- 		</p> -->

<%-- 	</form:form> --%>
	<%@ include file="/wysiwyg.html"%>
	<script>
		$(function() {
			$('#title').focus();
		});
	</script>
</body>
</html>