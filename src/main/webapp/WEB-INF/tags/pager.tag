<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="prevPage" required="true" rtexprvalue="true"%>
<%@ attribute name="nextPage" required="true" rtexprvalue="true"%>
<%@ attribute name="total" required="true" rtexprvalue="true"%>
<%@ attribute name="updatedTagId" required="true" rtexprvalue="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<div class="pager">
	<div><a class="previous 
<%-- 	<c:if test="${prevPage ge -1}">red</c:if> --%>
	" href="${prevPage}"> 
				<c:out value="<<"></c:out>
		</a>
	</div> <div><a class="next 
<%-- 	<c:if test="${nextPage lt 0}"> red</c:if> --%>
	" href="${nextPage}"> 
				<c:out value=">>"></c:out>
	</a></div>
</div>

<script>
	var pager = function(e, element, ref, next){
		e.preventDefault();
		if(ref == next){ return;}
		$.ajax({
	        url: '${ctx}/ajax/'+element+'/'+ref+'/${total}',
	        success: function(data) {
	        	var elementId = '#'+element;
	        	$(elementId).fadeOut('fast', function() {
	        	$(elementId).html(data);
	        		$(elementId).fadeIn('fast', function() {
// 					enableCycle(elementId);
				
	        		});
	        	});
				
	        },
	        error: function(data) {
		          alert(data);
		        	$(elementId).html(data);
		        }
	      });
	};
	var enableCycle = function(elementId){
		//$(elementId).parent().attr('class')
		var r = '#'+$(elementId).parent().attr("id")+' .sidebar-box';
		console.log(r);
		$(r).cycle({
			fx:     'scrollHorz', 
//			    delay:  -2000,
		    speed:  'fast',
		    timeout:  0,
		    pause:   1,
		    next:   elementId+' .previous', 
		    prev:   elementId+' .next' // choose your transition type, ex: fade, scrollUp, shuffle, etc...
		});
	};
$(function(){
	
	$('#${updatedTagId} .previous').click(function(e){
		pager(e, '${updatedTagId}', '${prevPage}', -1);
	});
	
	$('#${updatedTagId} .next').click(function(e){
		pager(e, '${updatedTagId}', '${nextPage}', 0);
	});
});
</script>