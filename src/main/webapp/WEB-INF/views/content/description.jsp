<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<p>Descripción</p>
<textarea rows="2" cols="10" name="body">
	<c:out value="${post.body}"></c:out>
	<myTags:errorField field="description"></myTags:errorField>
</textarea>