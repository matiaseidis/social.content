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

				
			</sec:authorize>