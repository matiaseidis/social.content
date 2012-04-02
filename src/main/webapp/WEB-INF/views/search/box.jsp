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
		$( "#searchTabs" ).tabs();
	});
</script>
<div id="searchTabs">
	<ul>
		<li><a href="#tabs-1">Contenidos</a></li>
		<li><a href="#tabs-2">Usuarios</a></li>
		<li><a href="#tabs-3">Etiquetas</a></li>
	</ul>
	<div id="tabs-1">	
	<form class="trigger-search" name="content">
		<input type="text" name="pattern" id="content-search-pattern"> 
		<input type="image" class="img-input"  title="Buscar" src='<c:url value="/img/symbolize-icons-set/png/16x16/search.png" />'>
	</form>
		<div id="content-result"></div>
	</div>
	<div id="tabs-2">
	<form class="trigger-search" name="user">
		<input type="text" name="pattern" id="user-search-pattern"> 
		<input type="image" class="img-input"  title="Buscar" src='<c:url value="/img/symbolize-icons-set/png/16x16/search.png" />'>
	</form>
	<div id="user-result" class="search-result"></div>
	</div>
	<div id="tabs-3">
	<form class="trigger-search" name="tag">
		<input type="text" name="pattern" id="tag-search-pattern"> 
		<input type="image" class="img-input" title="Buscar" src='<c:url value="/img/symbolize-icons-set/png/16x16/search.png" />'>
	</form>
	<div id="tag-result"></div>
	</div>
</div>
<script>

$('.trigger-search').submit(function(e){
	e.preventDefault();
	console.log($(e.target));
	var parentId = $(e.target).parent();
	console.log(parentId);
	var what = $(e.target).attr('name');
	console.log(what);
	var url = "${ctx}/ajax/search/"+what;
	console.log(url);
	var key = $('#'+what+'-search-pattern').val();
	console.log(key);
	if(key){
		$.ajax({
			url : url,
			data:{pattern:key},
			success : function(data) {
				$("#"+what+"-result").html(data);
				console.log($("#"+what+"-result"));
			},
			error : function(data) {
				alert(data);
			}
		});
	}
});
</script>