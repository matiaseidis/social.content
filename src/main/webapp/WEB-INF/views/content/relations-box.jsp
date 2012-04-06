<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="relations-box">
	<sec:authorize access="isAuthenticated()">
		<a href="#" id="relations-trigger"> <img
			src='<c:url value="/img/symbolize-icons-set/png/16x16/wired.png" />' />
			Relacionar esto
		</a>
		<script>
	$(function() {
		$('#relations-trigger').click(function(e) {
			$.ajax({
				type : 'GET',
				url : '<c:url value="/ajax/relationsBox" />',
				success : function(data) {
					$('#over-all-box').html(data);
					$('#over-all-box-wrapper').show();
				},
				error : function(data) {
					alert(data);
				}
			});
			return false;
			
		});
	});
</script>
	</sec:authorize>
	<div>
		<c:choose>
			<c:when test="${not empty content.relations}">
Relaciones:
</c:when>
			<c:otherwise>
Todavia sin relaciones...
</c:otherwise>
		</c:choose>

	</div>
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
	<sec:authorize access="isAnonymous()">registrese si quiere relacionar esto...</sec:authorize>
</div>
