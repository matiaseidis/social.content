<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myFunctions" uri="contentUtils"%>
<sec:authorize access="isAuthenticated()">
	<c:if test="${myFunctions:isContentFromLoggedInUser(content.id, model)}">
		<div class="delete-link">
			<a href="#" class="delete-content">eliminar(imagen aca)</a>
			<form action="../delete/${content.id}" method="POST">
			<input type="submit" value='Eliminar <c:out value="${contentType} (TODO -- pasar esto a un confirm que tire el post)"></c:out>' />
			</form>
		</div>
	</c:if>
</sec:authorize>
<script>
$(function(){
	$('.delete-link').click(function(e){
		e.preventDefault();
		if (confirm("El contenido dera borrado. Esta accion no se puede deshacer. ¿Esta seguro?")) {
			$.ajax({
				type: "POST",
		        url: '<c:url value="/content/${content.contentType}/delete/${content.id}" />',
		        success: function(data) {
		        	window.location = '<c:url value="/user/content" />';
		        },
		        error: function(data) {
			          alert(data);
			          $target.html(data);
			        }
		      });
			
		} 
	});
});
</script>