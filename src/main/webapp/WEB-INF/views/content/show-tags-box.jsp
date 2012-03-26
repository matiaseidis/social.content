<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:if test="${not empty content.tags}">
	<div>
		<p>
			<span>Etiquetas:</span>
		</p>

		<ul>
			<c:forEach var="tag" items="${content.tags}">
				<li><a href='<c:url value="/tag/show/${tag.tagName}"/>'><c:out
							value="${tag.tagName}"></c:out></a> <myTags:followUnfollowTag
						tag="${tag}" user="${user}"></myTags:followUnfollowTag></li>
			</c:forEach>
		</ul>
	</div>
	<myTags:errorField field="tags"></myTags:errorField>
</c:if>