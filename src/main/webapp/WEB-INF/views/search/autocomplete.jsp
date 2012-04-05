<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<script>
/* TODO mejores tabs, sun jquery UI
 * http://jqueryfordesigners.com/video.php?f=jquery-tabs-part1.flv
 */
	$(function() {
// 		$( "#searchTabs" ).tabs();
		var tabContainers = $('div.tabs > div');
		tabContainers.hide().filter(':first').show();
		
		$('div.tabs ul.tabNavigation li a').click(function(e){

			tabContainers.hide();
			tabContainers.filter(this.hash).show();
			$('div.tabs ul.tabNavigation li a').removeClass('selected');
			$(this).addClass('selected');
			return false;
		}).filter(':first').click();
	});
</script>
<div id="searchTabs" class="tabs">
	<ul class="tabNavigation">
		<li><a href="#tabs-1">Contenidos</a></li>
		<li><a href="#tabs-2">Usuarios</a></li>
		<li><a href="#tabs-3">Etiquetas</a></li>
	</ul>
	<div id="tabs-1" class="tab">	
	Buscar contenidos...
	<form id="trigger-content-relation-add" class="trigger-relation-add" name="content">
		<input type="text" class="medium fixed-height" name="pattern" id="content-relation-pattern">
		<input type="hidden" class="relation-element-id"  >  
		<input type="image" class="img-input"  title="Relacionar" src='<c:url value="/img/symbolize-icons-set/png/16x16/link.png" />'>
	</form>
		<div id="content-relation-result" class="relation-result"></div>
		<div class="relation-submit" name="content">
			<span><a href="#">Ok</a></span>
		</div>
	</div>
	<div id="tabs-2" class="tab">
	Buscar usuarios...
	<form id="trigger-user-relation-add" class="trigger-relation-add" name="user">
		<input type="text" class="medium fixed-height" name="pattern" id="user-relation-pattern">
		<input type="hidden" class="relation-element-id"  > 
		<input type="image" class="img-input"  title="Relacionar" src='<c:url value="/img/symbolize-icons-set/png/16x16/link.png" />'>
	</form>
	<div id="user-relation-result" class="search-result"></div>
	</div>
</div>
<style>
	.ui-autocomplete {
		max-height: 100px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
		/* add padding to account for vertical scrollbar */
		padding-right: 20px;
	}
	/* IE 6 doesn't support max-height
	 * we use height instead, but this forces the menu to always be this tall
	 */
	* html .ui-autocomplete {
		height: 100px;
	}
	</style>
<script>
$(function(){

	$('.relation-submit span a').click(function(e){
		var target = $(e.target).parent().parent().parent();
// 		console.log(target);
		var tId = target.attr('id');
		
		var relations = "";
		
		$.each($('#'+tId+' .relation-result').children(), function(index, value) { 
			var c = $(value).attr('class');
			var id = $('.'+c+' span.id').text();
// 			alert(id);
			var rel = $('.'+c+' .relations option:selected').attr('value');
			relations+=id+"_"+rel+"R";
		});
		console.log(relations);
		
		$.ajax({
			type: "POST",
	        url: '${ctx}/ajax/relations/add/',
	        data:{
	        	'relations':relations,
	        	'contentId':$('.content-id').attr('value')
	        	},
	        success: function(data) {
	        	
	        	$target.fadeOut('fast', function() {
	        		$target.html(data);
	        		$target.fadeIn('fast', function() {});
	      		});
	        },
	        error: function(data) {
		          alert(data);
		          $target.html(data);
		        }
	      });
		
	});
	
	var relationTypes = {
			1:"tiene que ver con", 
			2:"es horrible igual que"
			};
	
	$( "#content-relation-pattern" ).autocomplete({
		source: '<c:url value="/ajax/search/autocomplete/content" />',
		minLength: 2,
		select: function( event, ui ) {
			console.log($(event.target));
			$('#'+$(event.target).parent().attr('id')+' .relation-element-id').val(ui.item.id);
			addRelationElement(ui.item.id, ui.item.value, 'content');
			
			console.log( ui.item ?
				"Selected: " + ui.item.value + " aka " + ui.item.id :
				"Nothing selected, input was " + this.value );
		}
	});
	
	var addRelationElement = function(eId, eTitle, what){
		
		var eClass = eId;
		var element = $(document.createElement('div'));
		var p = $(document.createElement('p'));
		
		var identifier = $(document.createElement('span'));
		identifier.append(eTitle);
		
		var identifierId = $(document.createElement('span'));
		identifierId.append(eId);
		identifierId.css('display', 'none');

		/*
		 * trash button
		 */
		var i = $(document.createElement('img'));
		i.attr('src', "../../../img/symbolize-icons-set/png/16x16/trash.png");
		var a = $(document.createElement('a'));
		a.attr('href', '#');
		a.append(i);
		var trash = $(document.createElement('span'));
		trash.append(a);
		
		
		/*
		 * relation types select
		 */
		var relations = $(document.createElement('select'));
		$.each(relationTypes, function(key, value) { 
			var option = $(document.createElement('option'));
			option.attr('value', key);
			option.append(value);
			relations.append(option);
		});
		
		p.append(relations);
		p.append(identifier);
		p.append(trash);
		element.append(identifierId);
		element.append(p);
		
		var whatId = '#'+what+'-relation-result';
		if($(whatId+' .'+eClass).length == 0){
			$(whatId).prepend(element);
			$(whatId+' div:first-child').addClass(eClass.toString());
			$(whatId+' select:first-child').addClass("relations");
			$(whatId+' span:first-child').addClass("id");
// 			$(whatId+' p:first-child  span:first').addClass("name");
		}
	}
	
	
	$('.relation-result div p span a').live('click', function(e){
		var a = $(e.target);
		a.parent().parent().parent().parent().remove();
	});
	
	$(".trigger-relation-add").submit(function(e){
		var what = $(e.target).attr('name');
		var input = $("#"+what+"-relation-pattern");
		var eTitle = input.val();
		var formId = input.parent().attr('id');
		
		var eId = $('#'+formId + ' .relation-element-id').val(); 
		
		if($.trim(eId).length > 0 && $.trim(eTitle).length > 0) {
			addRelationElement(eId, eTitle, what);
		}
		return false;
	});
});
</script>