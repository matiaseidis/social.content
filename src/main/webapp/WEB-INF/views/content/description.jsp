<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p>Descripción</p>
<textarea rows="2" cols="10" name="body">
<c:choose>
	<c:when test="${not empty errors}">
					<c:out value="${errors.post.body}"></c:out>
				</c:when>
	<c:otherwise>
					<c:out value="${post.body}"></c:out>
				</c:otherwise>
</c:choose>
</textarea>