<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="contentType" required="true" rtexprvalue="true"%>
<%@ attribute name="contentList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ tag body-content="tagdependent"%>
<c:if test="${not empty contentList}">
	<div>
		<fieldset>
			<legend>
				<h3>${title} (${fn:length(contentList)})</h3>
			</legend>
			<ul>
				<c:forEach var="content" items="${contentList}">
					<li><a href="${pageContext.request.contextPath}/content/${contentType}/show/${content.title}">${content.title}</a></li>
				</c:forEach>
			</ul>
		</fieldset>
	</div>
</c:if>