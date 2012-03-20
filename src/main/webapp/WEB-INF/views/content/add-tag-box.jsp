<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${new eq null}">
		<jsp:include page="add-tag-existent-content-box.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="add-tag-new-content-box.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
<input type="hidden" name="plainTags" id="contentTags" />
