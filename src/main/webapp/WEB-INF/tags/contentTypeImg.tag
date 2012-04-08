<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="contentType" required="true" rtexprvalue="true" %>
<c:if test="${contentType eq 'video'}">
	<img
		src='<c:url value="/img/symbolize-icons-set/png/16x16/television.png" />' />
</c:if>

<c:if test="${contentType eq 'audio'}">
	<img
		src='<c:url value="/img/symbolize-icons-set/png/16x16/microphone.png" />' />
</c:if>

<c:if test="${contentType eq 'post'}">
	<img
		src='<c:url value="/img/symbolize-icons-set/png/16x16/page.png" />' />
</c:if>

<c:if test="${contentType eq 'event'}">
	<img
		src='<c:url value="/img/symbolize-icons-set/png/16x16/calendar_full.png" />' />
</c:if>


