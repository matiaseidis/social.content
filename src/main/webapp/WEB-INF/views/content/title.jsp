<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<p>
	<form:label for="title" path="title">Título</form:label>
	<br />
	<form:input path="title" class="long fixed-height" />
	<c:if test="${not empty errors}">
		<c:out value="${errors.title}"></c:out>
	</c:if>
</p>