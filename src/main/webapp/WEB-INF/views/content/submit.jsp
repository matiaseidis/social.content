<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p>
	<input type="submit" class="button"
		value="<c:choose><c:when test="${new eq null}">Actualizar</c:when><c:otherwise>Crear</c:otherwise></c:choose> ${entityName}" />
</p>
