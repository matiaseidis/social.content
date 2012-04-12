<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<div id="relation-list-wrapper">
<div>
	<c:choose>
		<c:when test="${not empty content.relations}">
Relaciones:
	<c:forEach items="${content.relations}" var="r">
				<div class="relation">

					<img
						src='<c:url value="/img/symbolize-icons-set/png/16x16/link.png" />' />
					<myTags:userImg height="25" width="25" username="${r.author}"
						hasOwnImage="${r.author.hasOwnImage}"></myTags:userImg>
					<a href='<c:url value="/profile/${r.author.userName}" />'> <c:out
							value="${r.author.userName}"></c:out>
					</a> dijo que esto <b><c:out value="${r.name}"></c:out></b> <a
						href='<c:url value="/content/${r.related.contentType}/show/${r.related.id}" />'>
						<c:out value="${r.related.title}"></c:out>
					</a>, de <a
						href='<c:url value="/profile/${r.related.author.userName}" />'>
						<c:out value="${r.related.author.userName}"></c:out>
					</a>, el
					<fmt:formatDate pattern="dd/MM/yyyy" dateStyle="short" type="date"
						value="${r.created}" timeZone="es" />
					<myTags:removeRelation relation="${r}" user="${user}"></myTags:removeRelation>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
Todavia sin relaciones...
</c:otherwise>
	</c:choose>

</div></div>