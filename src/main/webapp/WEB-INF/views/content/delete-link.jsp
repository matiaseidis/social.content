<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myFunctions" uri="contentUtils"%>
<sec:authorize access="isAuthenticated()">
	<c:if test="${myFunctions:isContentFromLoggedInUser(content.id, model)}">
		<div class="delete-link">
		<a href="../delete/${content.id}">Eliminar <c:out value="${contentType}"></c:out></a>
		</div>
	</c:if>
</sec:authorize>