<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript'
	src='${ctx}/js/mediaplayer-5.9-viral/jwplayer.js'></script>

<title><c:out value="${content.title}"></c:out></title>
</head>
<body>
<jsp:include page="../show-tags-box.jsp"></jsp:include>


	<div id='mediaspace'>This text will be replaced</div>
	<script type='text/javascript'>
		jwplayer('mediaspace')
				.setup(
						{
							'flashplayer' : '${ctx}/js/mediaplayer-5.9-viral/player.swf',
							'file' : '<c:out value="${content.videoRef}" ></c:out>',
							'controlbar' : 'bottom',
							'width' : '640',
							'height' : '360',
							'skin' : '${ctx}/js/video-js/skins/playcasso.zip'
						});
	</script>
	<%-- 'skin':'${pageContext.request.contextPath}/js/video-js/skins/playcasso/playcasso.zip'--%>
	<jsp:include page="../comments-box.jsp" />
</body>
</html>