<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="contentType" required="false" rtexprvalue="true"%>
<%@ attribute name="contentList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ tag body-content="tagdependent"%>
<c:if test="${not empty contentList}">
	<div>
				<h3>${title} (${fn:length(contentList)})</h3>
			<ul>
				<c:forEach var="content" items="${contentList}">
					<li><a href="${pageContext.request.contextPath}/content/${content.contentType}/show/${content.id}">${content.title}</a></li>
				</c:forEach>
			</ul>
	</div>
</c:if>