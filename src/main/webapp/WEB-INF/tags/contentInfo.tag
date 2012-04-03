<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="content" required="true" rtexprvalue="true" type="com.mati.demo.model.content.Content"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>


<div class="content-info">
<jsp:include page="/WEB-INF/views/content/author.jsp"></jsp:include>
	<p>
	Visitas: <c:out value="${content.visited}" />
	</p>
	
	<img src='<c:url value="/img/symbolize-icons-set/png/16x16/flag.png" />' />
		
	<ul>
	<c:forEach items="${content.tags}" var="tag">
		<li>
		<myTags:followUnfollowTag tag="${tag}" user="${user}"></myTags:followUnfollowTag>
		<a href='
		<c:url value="/tag/show/${tag.tagName}" />' ><c:out value="${tag.tagName}" /></a>
		(<c:out value="${fn:length(tag.taggedContent)}" />) 
		
		</li>
	</c:forEach>
	</ul>
</div>
