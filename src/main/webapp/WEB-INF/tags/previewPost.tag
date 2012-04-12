<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="content" required="false" rtexprvalue="true" type="com.mati.demo.model.content.Content"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<span> 
<a href='<c:url value="/content/post/show/${content.id}" />' >${content.title}</a>
</span>
<span>
<c:out value="${fn:substring(content.body,0,100)}" escapeXml="false"/>
</span>

-
<span>publicado el <fmt:formatDate dateStyle="short" type="date"
		value="${content.postDate}" timeZone="es" />
</span>

