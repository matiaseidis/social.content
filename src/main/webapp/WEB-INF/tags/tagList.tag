<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="tags" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ tag body-content="tagdependent"%>
<c:if test="${not empty tags}">
	<div>
				<h3>${title}</h3>
			<ul>
				<c:forEach var="tag" items="${tags}">
					<li>${tag.tagName}dd</li>
				</c:forEach>
			</ul>
	</div>
</c:if>