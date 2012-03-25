<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="prevPage" required="true" rtexprvalue="true"%>
<%@ attribute name="nextPage" required="true" rtexprvalue="true"%>
<%@ attribute name="updatedTagId" required="true" rtexprvalue="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<div class="pager">
	<span><a class="previous" href="${prevPage}"> <c:if
				test="${prevPage gt -1}">
				<c:out value="<<"></c:out>
			</c:if>
	</a></span> <span><a class="next" href="${nextPage}"> <c:if
				test="${nextPage gt 0}">
				<c:out value=">>"></c:out>
			</c:if>
	</a></span>
</div>

<script>
$(function(){
	$('#${updatedTagId} .previous').click(function(e){
		e.preventDefault();
		var prevPage = ${prevPage};
		if(prevPage == -1){
			return;
		}
		$.ajax({
	        url: '${ctx}/ajax/${updatedTagId}/'+prevPage+'/'+3,
	        success: function(data) {
	        	$('#${updatedTagId}').html(data);
	        },
	        error: function(data) {
		          alert(data);
		        	$('#${updatedTagId}').html(data);
		        }
	      });
	});
	$('#${updatedTagId} .next').click(function(e){
		e.preventDefault();
		var nextPage = ${nextPage};
		if(nextPage == 0){
			return;
		}
		var nextPage = $('#${updatedTagId} .next').attr('href');
		$.ajax({
	        url: '${ctx}/ajax/${updatedTagId}/'+nextPage+'/'+3,
	        success: function(data) {
	        	$('#${updatedTagId}').html(data);
	        },
	        error: function(data) {
		          alert(data);
		        	$('#${updatedTagId}').html(data);
		        }
	      });
	});
});
</script>