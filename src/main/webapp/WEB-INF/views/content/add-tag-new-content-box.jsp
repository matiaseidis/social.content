<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<div>
<input type="text" id="inputTag" class="medium fixed-height"/><span><a href="#" id="addTag">Agregar etiqueta</a></span>
<myTags:errorField field="tags"></myTags:errorField>
<p id="tagsPreview"></p>
</div>
