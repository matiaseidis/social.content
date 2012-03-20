<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Upload Example</title>
</head>
<body>
	<form:form action="${action}" modelAttribute="audio" method="post"
		enctype="multipart/form-data">

		<p>
			<form:label for="title" path="title">Name</form:label>
			<br />
			<form:input path="title" />
			<c:if test="${not empty errors}">
				<c:out value="${errors.title}"></c:out>
			</c:if>
		</p>
		<jsp:include page="../show-tags-box.jsp"></jsp:include>
		<jsp:include page="../add-tag-box.jsp"></jsp:include>
		<input type="hidden" name="plainTags" id="contentTags" />
		<h3>Body</h3>
		<p></p>
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
		<p>
			<form:label for="fileData" path="fileData">File: <c:out
					value="${audio.fileName}" />
			</form:label>
			<br />
			<form:input path="fileData" type="file" />

		</p>
		<p>
			<input type="submit" />
		</p>


	</form:form>
</body>
</html>
</body>
</html>