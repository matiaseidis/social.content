<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="comments-box">
	<h3>Comente esto viejo!</h3>
	<sec:authorize access="isAuthenticated()">
		<form:form action="${ctx}/comment/add/${contentType}/${content.id}"
			modelAttribute="comment" method="post">
			<form:label for="body" path="body"></form:label>
			<form:textarea cols="80" rows="4" path="body" id="new-comment-body" />
			<input type="hidden" id="comment-content-id" value="${content.id}" />
			<p>
				<input type="submit" id="comment" value="comentar" />
			</p>

		</form:form>
	</sec:authorize>
	<c:forEach items="${content.comments}" var="c">
		<div class="comment">
			<myTags:userImg height="50" width="50"
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
	<sec:authorize access="isAnonymous()">registrese si quiere commentar...</sec:authorize>
</div>
<%--
<div id="disqus_thread"></div>
<script type="text/javascript">
    /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
    var disqus_shortname = 'matiaseidis'; // required: replace example with your forum shortname

    /* * * DON'T EDIT BELOW THIS LINE * * */
    (function() {
        var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
        dsq.src = 'http://' + disqus_shortname + '.disqus.com/embed.js';
        (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
    })();
</script>
<noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
<a href="http://disqus.com" class="dsq-brlink">blog comments powered by <span class="logo-disqus">Disqus</span></a>
--%>

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
					alert('error');
					alert(data);
				}
			});
		});
	});
</script>