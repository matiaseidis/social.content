<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="rating-box">
	<p>
		<img class="rating-img"
			src='<c:url value="/img/symbolize-icons-set/png/16x16/chart.png" />' />
		Calificar esto
	</p>
	<sec:authorize access="isAuthenticated()">
		<div class="rating" id="rate1">
			<div class="cancel">
				<a title="Cancel Rating" href="#0">Cancel Rating</a>
			</div>
			<div class="star star-left on">
				<a title="Give it 0.5/5" href="#0.5" style="width: 100%;">0.5</a>
			</div>
			<div class="star star-right on">
				<a title="Give it 1/5" href="#1" style="width: 100%;">1</a>
			</div>
			<div class="star star-left on">
				<a title="Give it 1.5/5" href="#1.5" style="width: 100%;">1.5</a>
			</div>
			<div class="star star-right on">
				<a title="Give it 2/5" href="#2" style="width: 100%;">2</a>
			</div>
			<div class="star star-left on">
				<a title="Give it 2.5/5" href="#2.5" style="width: 100%;">2.5</a>
			</div>
			<div class="star star-right on">
				<a title="Give it 3/5" href="#3" style="width: 100%;">3</a>
			</div>
			<div class="star star-left on">
				<a title="Give it 3.5/5" href="#3.5" style="width: 100%;">3.5</a>
			</div>
			<div class="star star-right">
				<a title="Give it 4/5" href="#4" style="width: 100%;">4</a>
			</div>
			<div class="star star-left">
				<a title="Give it 4.5/5" href="#4.5" style="width: 100%;">4.5</a>
			</div>
			<div class="star star-right">
				<a title="Give it 5/5" href="#5" style="width: 100%;">5</a>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				$('#rate1').rating(
						'<c:url value="/ajax/rate/${content.id}" />', {
							maxvalue : 5,
							increment : .5,
							curvalue : <c:out value="${content.rate}" />
						});
			});
		</script>
	</sec:authorize>

	<sec:authorize access="isAnonymous()">registrese si quiere calificar esto...</sec:authorize>
</div>

