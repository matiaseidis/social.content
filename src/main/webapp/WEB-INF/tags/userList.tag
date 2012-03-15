<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="userList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ tag body-content="tagdependent"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:if test="${not empty userList}">
	<div>
		<h3>${title}</h3>

		<ul>
			<c:forEach var="u" items="${userList}">
				<li><a href="${ctx}/profile/${u.userName}">${u.userName}</a> <a
					href="${ctx}/profile/${u.userName}"><myTags:userImg height="50"
							width="50" username="${u.userName}"></myTags:userImg></a></li>
			</c:forEach>
		</ul>
	</div>
</c:if>

