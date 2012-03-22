<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${content.title}" escapeXml="true"></c:out></title>
</head>
<body>
<jsp:include page="../edit-link.jsp"></jsp:include>
<jsp:include page="../delete-link.jsp"></jsp:include>


	<div>
		<h6>
			Publicado por
			<c:out value="${content.author.userName}" escapeXml="true"></c:out>
			, el
			<fmt:formatDate pattern="dd/MM/yyyy" dateStyle="short" type="date"
				value="${content.postDate}" timeZone="es" />
		</h6>
		

			<jsp:include page="../show-tags-box.jsp"></jsp:include>

		<p><h4>
			<c:out value="${content.body}" escapeXml="false"></c:out>
		</h4>


	</div>

	<jsp:include page="../comments-box.jsp" />
</body>
</html>





					