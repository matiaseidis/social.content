<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${content.title}</title>
</head>
<body>
	<div>
<!-- 	<p> -->
<%-- 				<h1>${post.title}</h1> --%>
<!-- 				</p> -->
				<p>
				<h4>${content.body}</h4>
				</p>
	</div>
	
	<jsp:include page="../comments-box.jsp" />	
</body>
</html>