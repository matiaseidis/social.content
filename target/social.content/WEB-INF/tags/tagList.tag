<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="tagList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ tag body-content="tagdependent"%>
<c:if test="${not empty tagList}">
	<div>
				<h3>${title}</h3>
			<ul>
				<c:forEach var="tag" items="${tagList}">
					<li>${tag.tagName}</li>
				</c:forEach>
			</ul>
	</div>
</c:if>