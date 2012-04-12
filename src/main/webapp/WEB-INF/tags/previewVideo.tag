<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myFunctions" uri="contentUtils"%>
<%@ attribute name="content" required="false" rtexprvalue="true"
	type="com.mati.demo.model.content.Content"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>


<a href='<c:url value="/content/video/show/${content.id}" />'> <img
	src="${myFunctions:youTubeThumbnailUri(content)}" />
</a>
<span> 
<a href='<c:url value="/content/video/show/${content.id}" />' >${content.title}</a>
</span>
-
<!-- <span>publicado por  -->
<%-- <a href='<c:url value="/profile/${content.author.userName}" />'>${content.author.userName}</a> --%>
<!-- </span> -->
<span>publicado el <fmt:formatDate dateStyle="short" type="date"
		value="${content.postDate}" timeZone="es" />
</span>



