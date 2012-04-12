$(function(){
	
	$('#inputTag').keypress(function(e){
		var code = (e.keyCode ? e.keyCode : e.which);
		if(code == 13) { 
			$('#addTag').click();			
			return false;	
		}
	});
	
	$('#inputTag').val("");
	
	$('#addTag').click(function(){
		var tag = $('#inputTag').val();
		
		if(tag){
			var newTag = $.trim(tag) + ",";
			
			var duplicated = $('#contentTags').val().toLowerCase().indexOf(newTag.toLowerCase()) >= 0;
			if(!duplicated){
				$('#contentTags').val($('#contentTags').val()+newTag);
				$('#inputTag').val("").focus();
				var removeLink = "<a href='#' class='removeTagPreview'>x</a>";
				$('#tagsPreview').append("<span class='tagPreview'><span class='tagName'>"+tag+"</span>"+removeLink+"</span>");	
			}
		} 
	});
	$('.removeTagPreview').live('click', function(e){
		
		var tagName = $(this).parent().find('.tagName').html();
		
		var result = $('#contentTags').val().replace(tagName+",",'');
		$('#contentTags').val(result);
		
		$(this).parent().remove();
	});
});