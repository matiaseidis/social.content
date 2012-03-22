<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myFunctions" uri="contentUtils"%>
<sec:authorize access="isAuthenticated()">
	<c:if test="${myFunctions:isContentFromLoggedInUser(content.id, model)}">
		<div class="delete-link">
			<form action="../delete/${content.id}" method="POST">
			<input type="submit" value='Eliminar <c:out value="${contentType} (TODO -- pasar esto a un confirm que tire el post)"></c:out>' />
			</form>
		</div>
	</c:if>
</sec:authorize>