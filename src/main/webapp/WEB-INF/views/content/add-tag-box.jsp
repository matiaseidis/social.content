<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%-- <c:choose> --%>
<%-- 	<c:when test="${new eq null}"> --%>
<%-- 		<jsp:include page="add-tag-existent-content-box.jsp"></jsp:include> --%>
<%-- 	</c:when> --%>
<%-- 	<c:otherwise> --%>
<%-- 		<jsp:include page="add-tag-new-content-box.jsp"></jsp:include> --%>
<%-- 	</c:otherwise> --%>
<%-- </c:choose> --%>
<!-- <input type="hidden" name="plainTags" id="contentTags" /> -->
<script type="text/javascript" src="${ctx}/js/app/tags.js"></script>
<div>
	<input type="text" id="inputTag" class="medium fixed-height" /><span><a
		href="#" id="addTag">Agregar etiqueta</a></span>
	<myTags:errorField field="tags"></myTags:errorField>
	<c:choose>
		<c:when test="${errors.plainTagsList ne null}">
			<c:set var="tags" value="${errors.plainTagsList}"></c:set>
			<c:set var="plainTags" value="${errors.plainTags}"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="tags" value="${content.tags}"></c:set>
			<c:set var="plainTags" value=""></c:set>
		</c:otherwise>
	</c:choose>

	<p id="tagsPreview">
		<c:forEach items="${tags}" var="tag">
			<span class="tagPreview"> <span class="tagName"><c:out
						value="${tag.tagName}"></c:out></span> <a href="#"
				class="removeTagPreview">x</a>
			</span>
		</c:forEach>
	</p>
	<input type="hidden" name="plainTags" id="contentTags" value="${plainTags}"/>
</div>