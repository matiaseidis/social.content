<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="user" required="true" rtexprvalue="true"
	type="com.mati.demo.model.user.User"%>
<%@ attribute name="relation" required="true" rtexprvalue="true"
	type="com.mati.demo.model.relationships.Relation"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="myFunctions" uri="isFollowedBy"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<c:if test="${myFunctions:isOwnRelation(relation, user)}">
		<a href="#" class="remove-relation" name="${relation.code}"
			title="Eliminar relacion"
			onclick='removeRelation(event, "${relation.id}", "${content.id}")'>
			<img class="remove-relation" width="8px"
			src='<c:url value="/img/symbolize-icons-set/png/16x16/minus.png" />' />
	</c:if>
</sec:authorize>
