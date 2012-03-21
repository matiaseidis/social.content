<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:if test="${not empty content.tags}">
	<div>
		<p>
			<span>Etiquetas:</span>
		</p>

		<ul>
			<c:forEach var="tag" items="${content.tags}">
				<li><a href="${ctx}/tag/show/${tag.tagName}"><c:out
							value="${tag.tagName}"></c:out></a></li>
			</c:forEach>
		</ul>
	</div>
	<myTags:errorField field="tags"></myTags:errorField>
</c:if>