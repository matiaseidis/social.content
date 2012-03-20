<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myFunctions" uri="videoThumbnail"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="videoList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>


<%-- <%@ tag body-content="tagdependent"%> --%>
<c:if test="${not empty videoList}">
	<div>
		<h3>${title} (${fn:length(videoList)})</h3>
		<ul>
			<c:forEach var="content" items="${videoList}">
				<li><span> <myTags:userImg height="50" width="50"
							username="${content.author.userName}"></myTags:userImg>
				</span> <a href="${ctx}/content/video/show/${content.id}"> 
				<img src="${myFunctions:youTubeThumbnailUri(content)}" />
				</a> <span> <a href="${ctx}/content/video/show/${content.id}">${content.title}</a>
				</span> - <span>publicado por <a
						href="${ctx}/profile/${content.author.userName}">${content.author.userName}</a>
				</span> <span>el <fmt:formatDate dateStyle="short" type="date"
							value="${content.postDate}" timeZone="es" />
				</span></li>
			</c:forEach>
		</ul>
	</div>
</c:if>