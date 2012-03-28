<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="prevPage" required="true" rtexprvalue="true"%>
<%@ attribute name="nextPage" required="true" rtexprvalue="true"%>
<%@ attribute name="total" required="true" rtexprvalue="true"%>
<%@ attribute name="updatedTagId" required="true" rtexprvalue="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<div class="pager">
	<div><a class="previous <c:if test="${prevPage ge -1}">red</c:if>" href="${prevPage}"> 
				<c:out value="<<"></c:out>
		</a>
	</div> <div><a class="next <c:if test="${nextPage lt 0}"> red</c:if>" href="${nextPage}"> 
				<c:out value=">>"></c:out>
	</a></div>
</div>

<script>
$(function(){
	$('#${updatedTagId} .previous').click(function(e){
		e.preventDefault();
		var prevPage = ${prevPage};
		if(prevPage == -1){ return;	}
		$.ajax({
	        url: '${ctx}/ajax/${updatedTagId}/${prevPage}/${total}',
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
		if(nextPage == 0){ return; }
		$.ajax({
	        url: '${ctx}/ajax/${updatedTagId}/${nextPage}/${total}',
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