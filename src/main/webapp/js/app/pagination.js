$(function(){
	
	alert(9);
	$('#previous').click(function(e){
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
	$('#next').click(function(e){
		e.preventDefault();
		var nextPage = ${nextPage};
		if(nextPage == 0){
			return;
		}
		var nextPage = $('#next').attr('href');
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