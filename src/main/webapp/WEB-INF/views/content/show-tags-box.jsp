<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
	
<c:if test="${not empty content.tags}">
	<div><p>Tags</p>
			<ul>
				<c:forEach var="tag" items="${content.tags}">
					<li><a href="${ctx}/tag/show/${tag.tagName}"><c:out value="${tag.tagName}"></c:out></a></li>
				</c:forEach>
			</ul>
		
	</div>
</c:if>