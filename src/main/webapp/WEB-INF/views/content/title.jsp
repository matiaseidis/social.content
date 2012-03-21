<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<p>
	<form:label for="title" path="title">Título</form:label>
	<br />
	<form:input path="title" class="medium fixed-height" />
	<myTags:errorField field="title"></myTags:errorField>
</p>