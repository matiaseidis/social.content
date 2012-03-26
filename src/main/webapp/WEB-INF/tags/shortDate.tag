<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="title" required="false" rtexprvalue="true"%>
<%@ attribute name="date" required="true" rtexprvalue="true"
	type="java.util.Date"%><c:if test="${date ne null}">
	<c:if test="${title ne null}">
		<c:out value="${title}"></c:out>
			:
</c:if>
	<fmt:formatDate pattern="dd/MM/yyyy" dateStyle="short" type="date"
		value="${date}" timeZone="es" />
</c:if>