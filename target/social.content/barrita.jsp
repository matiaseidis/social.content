<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<sec:authorize access='isAnonymous()'>
		<jsp:include page="/login.jsp"></jsp:include>
	</sec:authorize>
<%-- 	<sec:accesscontrollist hasPermission="" domainObject=""></sec:accesscontrollist> --%>
	<sec:authorize access="isAuthenticated()">
		<jsp:include page="/WEB-INF/views/loggedIn.jsp"></jsp:include>
	</sec:authorize>