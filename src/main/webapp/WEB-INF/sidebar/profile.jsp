<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
	<sec:authorize access="isAuthenticated()">

				
				<div class="sidebar-box-wrapper" id="content-sidebar-box-wrapper">
					<div id="followedContent" class="sidebar-box"></div>
				</div>
				<div class="sidebar-box-wrapper users-sidebar-box-wrapper">
					<div id="followedUsers" class="sidebar-box"></div>
				</div>
				<div class="sidebar-box-wrapper users-sidebar-box-wrapper">
					<div id="followedBy" class="sidebar-box"></div>
				</div>
				<div class="sidebar-box-wrapper">
					<div id="followedEvents" class="sidebar-box"></div>
				</div>
				<div class="sidebar-box-wrapper">
					<div id="followedTags" class="sidebar-box"></div>
				</div>

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