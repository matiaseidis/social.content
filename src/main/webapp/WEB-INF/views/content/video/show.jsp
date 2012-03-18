<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript'
	src='${pageContext.request.contextPath}/js/mediaplayer-5.9-viral/jwplayer.js'></script>

<title>${content.title}</title>
</head>
<body>
	<div id='mediaspace'>This text will be replaced</div>
	<script type='text/javascript'>
		jwplayer('mediaspace')
				.setup(
						{
							'flashplayer' : '${pageContext.request.contextPath}/js/mediaplayer-5.9-viral/player.swf',
							'file' : '<c:out value="${content.videoRef}" ></c:out>',
							'controlbar' : 'bottom',
							'width' : '640',
							'height' : '360',
							'skin' : '${pageContext.request.contextPath}/js/video-js/skins/grungetape.zip'
						});
	</script>
	<%-- 'skin':'${pageContext.request.contextPath}/js/video-js/skins/playcasso/playcasso.zip'--%>
	<jsp:include page="../comments-box.jsp" />
</body>
</html>