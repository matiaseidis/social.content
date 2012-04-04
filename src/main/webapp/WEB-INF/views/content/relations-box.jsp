<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="relations-box">
	<sec:authorize access="isAuthenticated()">
	<a href="#" id="relations-trigger">
	<img src='<c:url value="/img/symbolize-icons-set/png/16x16/link.png" />' />
	Relacionar esto</a>
	
<script>
	$(function() {
		$('#relations-trigger').click(function(e) {
			$.ajax({
				type : 'GET',
				url : '<c:url value="/ajax/relationsBox" />',
				success : function(data) {
					$('#over-all-box').html(data);
					$('#over-all-box-wrapper').show()
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
	<c:forEach items="${content.relations}" var="r">
		<div class="relation">
		<img src='<c:url value="/img/symbolize-icons-set/png/16x16/link.png" />' />
			<myTags:userImg height="25" width="25"
				username="${r}"  hasOwnImage="${user.hasOwnImage}"></myTags:userImg>
			<p>
				<c:out value="${r}"></c:out>
			</p>
			<h6>
				Relacionado por
				<c:out value="${r}"></c:out>
				, el
<%-- 				<fmt:formatDate pattern="dd/MM/yyyy" dateStyle="short" type="date" --%>
<%-- 					value="${c.postDate}" timeZone="es" /> --%>

			</h6>
		</div>
	</c:forEach>
	<sec:authorize access="isAnonymous()">registrese si quiere relacionar esto...</sec:authorize>
</div>
