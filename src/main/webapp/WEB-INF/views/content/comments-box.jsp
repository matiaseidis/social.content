<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="comments-box">
	<p>
	<img src='<c:url value="/img/symbolize-icons-set/png/16x16/comments.png" />' />
	Comente esto viejo!</p>
	<sec:authorize access="isAuthenticated()">
		<form:form action="${ctx}/comment/add/${contentType}/${content.id}"
			modelAttribute="comment" method="post">
<%-- 			<form:label for="body" path="body"></form:label> --%>
			<form:textarea cols="80" rows="4" path="body" id="new-comment-body" />
			<input type="hidden" id="comment-content-id" value="${content.id}" />
			<p>
				<input type="submit" id="comment" value="comentar" />
			</p>

		</form:form>
		<script>
	$(function() {
		$('#comment').bind('submit', function(e) {
			e.preventDefault();
			var newComment = $('#new-comment-body').val();
			var contentType = $('#comment-content-type').val();
			var contentId = $('#comment-content-id').val();

			var c = {
				body : newComment
			};
			$.ajax({
				type : 'POST',
				url : '${ctx}/ajax/comment/' + contentId,
				data : c,
				success : function(data) {
					$('#comments-box').html(data);
					$('#new-comment-body').val('').focus();
				},
				error : function(data) {
					alert(data);
				}
			});
		});
	});
</script>
	</sec:authorize>
	<c:forEach items="${content.comments}" var="c">
		<div class="comment">
		<img src='<c:url value="/img/symbolize-icons-set/png/16x16/comments.png" />' />
			<myTags:userImg height="25" width="25"
				username="${c.author.userName}"  hasOwnImage="${user.hasOwnImage}"></myTags:userImg>
			<p>
				<c:out value="${c.body}"></c:out>
			</p>
			<h6>
				Comentado por
				<c:out value="${c.author.userName}"></c:out>
				, el
				<fmt:formatDate pattern="dd/MM/yyyy" dateStyle="short" type="date"
					value="${c.postDate}" timeZone="es" />

			</h6>
		</div>
	</c:forEach>
	<sec:authorize access="isAnonymous()">registrese si quiere comentar...</sec:authorize>
</div>
