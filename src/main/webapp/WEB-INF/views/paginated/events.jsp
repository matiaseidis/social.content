<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:if test="${not empty followedList}">
		<div>
			<h3>${title}</h3>
			<ul>
				<c:forEach var="content" items="${followedList}">
					<li><span> <myTags:userImg height="50" width="50"
								username="${content.author.userName}"></myTags:userImg>
					</span> <span> <a
							href="${ctx}/content/${content.contentType}/show/${content.id}">${content.title}</a>
					</span> - <span>el <fmt:formatDate dateStyle="short" type="date"
								value="${content.start}" timeZone="es" />
					</span></li>
				</c:forEach>
			</ul>
		</div>
<myTags:pager nextPage="${nextPage}" prevPage="${prevPage}" updatedTagId="${updatedTagId}"></myTags:pager>
</c:if>