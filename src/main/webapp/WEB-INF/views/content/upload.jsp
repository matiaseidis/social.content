<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<p>
	<form:label for="fileData" path="fileData">Archivo: <c:out
			value="${content.fileName}" />
	</form:label>

	<form:input path="fileData" type="file" />
	<myTags:errorField field="url"></myTags:errorField>
	
	<form:hidden path="fileName" />

</p>