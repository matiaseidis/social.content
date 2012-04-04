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
	<form class="trigger-search" name="content">
		<input type="text" class="medium fixed-height" name="pattern" id="content-search-pattern"> 
		<input type="image" class="img-input"  title="Buscar" src='<c:url value="/img/symbolize-icons-set/png/16x16/search.png" />'>
	</form>
		<div id="content-result"></div>
	</div>
	<div id="tabs-2" class="tab">
	Buscar usuarios...
	<form class="trigger-search" name="user">
		<input type="text" class="medium fixed-height" name="pattern" id="user-search-pattern"> 
		<input type="image" class="img-input"  title="Buscar" src='<c:url value="/img/symbolize-icons-set/png/16x16/search.png" />'>
	</form>
	<div id="user-result" class="search-result"></div>
	</div>
	<div id="tabs-3" class="tab">
	Buscar etiquetas...
	<form class="trigger-search" name="tag">
		<input type="text" class="medium fixed-height" name="pattern" id="tag-search-pattern"> 
		<input type="image" class="img-input" title="Buscar" src='<c:url value="/img/symbolize-icons-set/png/16x16/search.png" />'>
	</form>
	<div id="tag-result"></div>
	</div>
</div>
<script>
$(function(){
	$( "#content-search-pattern" ).autocomplete({
		source: '<c:url value="/ajax/search/autocomplete/content" />',
		minLength: 2,
		select: function( event, ui ) {
			console.log( ui.item ?
				"Selected: " + ui.item.value + " aka " + ui.item.id :
				"Nothing selected, input was " + this.value );
		}
	});
});
</script>