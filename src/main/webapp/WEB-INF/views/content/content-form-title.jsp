<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${new eq null}">Editar</c:when>
	<c:otherwise>Nuevo</c:otherwise>
</c:choose> ${entityName}