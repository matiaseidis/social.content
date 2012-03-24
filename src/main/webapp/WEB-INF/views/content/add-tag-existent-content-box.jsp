<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<script type="text/javascript" src="${ctx}/js/app/tags.js"></script>
<div>
<input type="text" id="inputTag" class="medium fixed-height"/><span><a href="#" id="addTag">Agregar etiqueta</a></span>
<myTags:errorField field="tags"></myTags:errorField>
<p id="tagsPreview">
<c:forEach items="${content.tags}" var="tag">
	<span class="tagPreview">
		<span class="tagName"><c:out value="${tag.tagName}"></c:out></span>
		<a href="#" class="removeTagPreview">x</a>
	</span>
</c:forEach>
</p>
</div>
