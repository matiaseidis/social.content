<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <c:set var="req" value="${pageContext.request}" /> --%>
<%-- <c:set var="uri" value="${req.requestURI}" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
<c:choose>
			<c:when test="${not empty post}">Edit</c:when>
			<c:otherwise>New</c:otherwise>
		</c:choose>
		 Post</title>
</head>
<body>
	<h1>
		<c:choose>
			<c:when test="${not empty post}">Edit</c:when>
			<c:otherwise>New</c:otherwise>
		</c:choose>

		Post
	</h1>
	<form action="<c:out value="${action}"></c:out>" method=POST>
		<h3>Title</h3>
		<p></p>
		<input type="text" name="title"
			value="<c:out value="${post.title}"></c:out>">

		<c:if test="${not empty errors}">
			<c:out value="${errors.title}"></c:out>
		</c:if>
		<p />
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
		<p></p>
		<input type="submit">
	</form>


	<%@ include file="/wysiwyg.html"%>

</body>
</html>