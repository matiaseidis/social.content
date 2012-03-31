<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:if test="${not empty followedList}">
	<div>
		<h3>${title} (${total} de ${fullTotal})</h3>
		<ul>
			<c:forEach var="t" items="${followedList}">
				<li class="${t.tagName}"><a
					href='<c:url value="/tag/show/${t.tagName}" ></c:url>'> <c:out
							value="${t.tagName}" />
				</a> <myTags:followUnfollowTag tag="${t}" user="${user}"></myTags:followUnfollowTag>

				</li>
			</c:forEach>
		</ul>
	</div>
	<myTags:pager nextPage="${nextPage}" prevPage="${prevPage}"
		total="${total}" updatedTagId="${updatedTagId}"></myTags:pager>
</c:if>