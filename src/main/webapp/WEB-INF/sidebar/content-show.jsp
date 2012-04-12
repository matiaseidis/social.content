<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<c:if test='${content ne null and action ne "create"}'>
	<div class="sidebar-box-wrapper" id="content-info-box-wrapper">
		<myTags:contentInfo content="${content}"></myTags:contentInfo>
	<jsp:include page="/WEB-INF/views/content/rating-box.jsp" />
	<div id="relation-box-wrapper">
		<jsp:include page="/WEB-INF/views/content/relations-box.jsp" />
	</div>
	
	<br />
	<hr />
	<br />
	<myTags:contentPreview contentList="${moreOfThisAuthor}" ></myTags:contentPreview>
<%-- 	<myTags:contentList contentList="${moreOfThisAuthor}" title="moreOfThisAuthor"></myTags:contentList> --%>
	</div>
</c:if>

