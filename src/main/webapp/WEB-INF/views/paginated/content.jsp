<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:if test="${not empty followedList}">
	<div>
		<h3>${title} (${total})</h3>
		<ul>
			<c:forEach var="content" items="${followedList}">
				<li><span> <myTags:userImg  hasOwnImage="${user.hasOwnImage}" height="${imgSize}" width="${imgSize}"
							username="${content.author.userName}"></myTags:userImg>
				</span> <span> <a
						href="${ctx}/content/${content.contentType}/show/${content.id}">${content.title}</a>
				</span> - <span>por <a
						href="${ctx}/profile/${content.author.userName}">${content.author.userName}</a>
				</span> <span>el <fmt:formatDate dateStyle="short" type="date"
							value="${content.postDate}" timeZone="es" />
				</span></li>
			</c:forEach>
		</ul>
	</div>
	<myTags:pager nextPage="${nextPage}" prevPage="${prevPage}" total="${total}"
		updatedTagId="${updatedTagId}"></myTags:pager>
</c:if>
