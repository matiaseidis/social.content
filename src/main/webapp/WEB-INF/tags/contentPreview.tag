<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="contentList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:if test="${not empty contentList}">
	<div>
		<ul>
			<c:forEach var="content" items="${contentList}">
				<li>
				<myTags:contentTypeImg contentType="${content.contentType}" />
				<c:if test="${content.contentType eq 'video'}">
						<myTags:previewVideo content="${content}" />
					</c:if> 
					<c:if test="${content.contentType eq 'audio'}">
						<myTags:previewAudio content="${content}" />
					</c:if> 
					<c:if test="${content.contentType eq 'post'}">
						<myTags:previewPost content="${content}" />
					</c:if> 
					<c:if test="${content.contentType eq 'event'}">
						<myTags:previewEvent content="${content}" />
					</c:if> 
					<hr />
				</li>	
				</c:forEach>
		</ul>
	</div>
</c:if>

