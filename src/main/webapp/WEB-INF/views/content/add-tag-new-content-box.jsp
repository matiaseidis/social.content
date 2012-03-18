<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<div>
<input type="text" id="inputTag" /><span><a href="#" id="addTag">Agregar etiqueta</a></span>
<p id="tagsPreview"></p>
</div>
<script>
$(function(){
	$('#contentTags').val("");
	$('#inputTag').val("");
	
	$('#addTag').click(function(){
		var tag = $('#inputTag').val()
		if(tag){
			var newTag = $.trim(tag) + ",";
			
			var duplicated = $('#contentTags').val().toLowerCase().indexOf(newTag.toLowerCase()) >= 0;
			if(!duplicated){
				$('#contentTags').val($('#contentTags').val()+newTag);
				$('#inputTag').val("").focus();
				var removeLink = "<a href='#' class='removeTagPreview'>x</a>";
				$('#tagsPreview').append("<span class='tagPreview'><span class='tagName'>"+tag+"</span>"+removeLink+"</span>");	
			}
		} else{
			//ignore
		}
		
	});
	$('.removeTagPreview').live('click', function(e){
		
		var tagName = $(this).parent().find('.tagName').html();
		
		var result = $('#contentTags').val().replace(tagName+",",'');
		$('#contentTags').val(result);
		
		$(this).parent().remove();
		
	});
});
</script>
