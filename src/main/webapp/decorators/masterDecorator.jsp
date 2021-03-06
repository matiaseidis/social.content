<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/reset.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/fonts.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/layout.css' />" />
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<c:url value='/js/jquery/ui/css/hot-sneaks/jquery-ui-1.8.18.custom.css' />" /> --%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/js/jquery/ui/css/custom/jquery-ui-1.8.18.custom.css' />" />

<script type="text/javascript"
	src="<c:url value='/js/jquery/jquery-1.7.1.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery/ui/js/jquery-ui-1.8.18.custom.min.js' />"></script>

<script type="text/javascript"
	src="<c:url value='/js/jquery/rating/jquery.rating.js' />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/js/jquery/rating/rating.css' />" />
<!-- 	<script src="http://cloud.github.com/downloads/malsup/cycle/jquery.cycle.all.latest.js" type="text/javascript"></script> -->

<title>Social Content :: <sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
</head>
<body>

	<div class="barrita-wrapper">
		<div class="barrita">
			<jsp:include page="/barrita.jsp"></jsp:include>
		</div>
	</div>
	<div class="page-wrapper">
		<c:if test="${message ne null}">
			<div class="message">
				<p>${message}</p>
			</div>
			<% session.setAttribute("message", null); %>
		</c:if>
		<c:if test="${sessionScope.errors ne null}">
			<div class="errorblock">
				<p>${errors}</p>
				<% session.setAttribute("errors", null); %>
			</div>
		</c:if>
		<div class='mainBody'>
			<a href="#" id="search-box-trigger"> <img
				src='<c:url value="/img/symbolize-icons-set/png/16x16/search.png" />' />
			</a>
			<div class="search-box-wrapper">
				<div id="search-box" class="search-box"></div>
			</div>
			<div class="title">
				<h1 class='title'>
					<sitemesh:write property='title' />
				</h1>
			</div>
			<sitemesh:write property='body' />
		</div>
		<div class='sidebar'>
			<!-- 			sidebar right -->
			<script>
// 			$('#loading-box-wrapper').show();
			$(function(){
				$("#loading-box-wrapper").ajaxStart(function(){
				    $(this).fadeIn('fast'); //show();
				 }).ajaxStop(function(){
				    $(this).fadeOut('fast'); //hide();
				 });
			});

			/*
			 *public
			 */
			 $('#search-box-trigger').click(function(e){
					e.preventDefault();
					console.log($('div#search-box').is(":empty"));
					var searchBoxId = "#search-box";
					if($('div#search-box').is(":empty")){
						$.ajax({
							url : '${ctx}/ajax/searchBox',
							success : function(data) {
								$(searchBoxId).hide();
								$(searchBoxId).html(data);
								$(searchBoxId).fadeIn('fast', function() {
										
								});
								
								
							},
							error : function(data) {
								alert(data);
								$(elementId).html(data);
							}
						});
					} else{
						$(searchBoxId).fadeOut('fast', function(){
							$(searchBoxId).empty();
						});
					}
					
				});
			</script>
			<c:if test="${sidebar ne null}">
				<jsp:include page="../WEB-INF/sidebar/${sidebar}.jsp"></jsp:include>
			</c:if>



		</div>

		<div class='disclaimer'>Site disclaimer. This is an example.</div>
	</div>
	<div id="over-all-box-wrapper">
		<div id="over-all-box"></div>
	</div>

	<div id="loading-box-wrapper">
		<div id="loading-box">
			<p>
				momento...<br /> <img src='<c:url value="/img/ajax-loader.gif" />' />
			</p>
		</div>
	</div>
	<sec:authorize access="isAuthenticated()">
		<script>
					$(function() {
						var paginations = [ "followedContent", "followedUsers",
								"followedBy", "followedEvents", "followedTags" ];
						
						$.each(paginations, function(index, value) {
							paginate(value);
						});
					});
					
					
					
					var paginate = function(value) {
						var elementId = "#" + value;
						$.ajax({
							url : '${ctx}/ajax/' + value + '/0/100',
							success : function(data) {
								$(elementId).fadeOut('fast', function() {
									$(elementId).html(data);
									$(elementId).fadeIn('fast', function() {
										
									});
								});
								
							},
							error : function(data) {
								alert(data);
								$(elementId).html(data);
							}
						});
					};
					var followUnfollow = function(event, name, entity){
						event.preventDefault();
						var $target = $(event.target).closest('div');
						$.ajax({
							type: "POST",
					        url: '${ctx}/ajax/'+entity+'/followUnfollow/'+name,
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
					};
					
					var removeRelation = function(event, relationId, relatedId){
						event.preventDefault();
						var $target = $(event.target).closest('div.relation');
						console.log($target);
						$.ajax({
							type: "POST",
					        url: '${ctx}/ajax/relation/remove',
					        data:{
					        	'relationId':relationId,
					        	'relatedId':relatedId
					        },
					        success: function(data) {
					        	$target.fadeOut('fast', function() {
					        		$target.empty().html(data).fadeIn('fast');
					      		});
					        },
					        error: function(data) {
						          alert(data);
						          $target.html(data);
						        }
					      });
					};
				</script>
	</sec:authorize>
</body>
</html>