<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="contentType" required="false" rtexprvalue="true"%>
<%@ attribute name="contentList" required="true" rtexprvalue="true"
	type="java.util.List"%>
	<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
	
<%@ tag body-content="tagdependent"%>
<c:if test="${not empty contentList}">
	<div>
				<h3>${title} (${fn:length(contentList)})</h3>
			<ul>
				<c:forEach var="content" items="${contentList}">
					<li>
					<span>
					<myTags:userImg height="50" width="50" username="${content.author.userName}"></myTags:userImg>
					</span>
					<span>
					<a href="${pageContext.request.contextPath}/content/${content.contentType}/show/${content.id}">${content.title}</a>
					</span>
					-
					<span>publicado por 
					<a href="${pageContext.request.contextPath}/profile/${content.author.userName}">${content.author.userName}</a>
					</span>
					<span>el 
					<fmt:formatDate dateStyle="short" type="date" value="${content.postDate}"
							timeZone="es" />
					</span>
					
					</li>
				</c:forEach>
			</ul>
	</div>
</c:if>