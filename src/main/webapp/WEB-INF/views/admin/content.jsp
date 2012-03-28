<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<%-- <myTags:contentList contentList="${content}" title="Content" --%>
<%-- 	contentType="content"></myTags:contentList> --%>

<div id="allContent"></div>
<script>
					$(function() {
						var paginations = [ "allContent"];
						var paginate = function(value) {
							var elementId = "#" + value;
							$.ajax({
								url : '${ctx}/ajax/' + value + '/0/100',
								success : function(data) {
									$(elementId).html(data);
								},
								error : function(data) {
									alert(data);
									$(elementId).html(data);
								}
							});
						};

						$.each(paginations, function(index, value) {
							paginate(value);
						});
					});
				</script>
