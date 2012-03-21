<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript'
	src='${ctx}/js/mediaplayer-5.9-viral/jwplayer.js'></script>

<script type="text/javascript" language="javascript"
	src="${ctx}/js/niftyplayer/niftyplayer.js"></script>


<title><c:out value="${content.title}"></c:out></title>
</head>
<body>
<jsp:include page="../edit-link.jsp"></jsp:include>
<!-- <div class="player-1"> -->
<!-- 	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" -->
<!-- 		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" -->
<!-- 		width="165" height="38" id="niftyPlayer1" align=""> -->
<!-- 		<param name=movie -->
<%-- 			value="${ctx}/js/niftyplayer/niftyplayer.swf?file=<c:out value="${content.audioRef}" ></c:out>&as=1"> --%>
<!-- 		<param name=quality value=high> -->
<!-- 		<param name=bgcolor value=#FFFFFF> -->
<!-- 		<embed -->
<%-- 			src="${ctx}/js/niftyplayer/niftyplayer.swf?file=<c:out value="${content.audioRef}" ></c:out>&as=1" --%>
<!-- 			quality=high bgcolor=#FFFFFF width="165" height="38" -->
<!-- 			name="niftyPlayer1" align="" type="application/x-shockwave-flash" -->
<!-- 			pluginspage="http://www.macromedia.com/go/getflashplayer"> -->
<!-- 		</embed> -->
<!-- 	</object> -->
<!-- 	</div> -->
<!-- 	<p></p> -->
<div class="player-2">
	<object type="application/x-shockwave-flash"
		data="${ctx}/js/dewplayer/dewplayer.swf" width="200" height="20"
		id="dewplayer" name="dewplayer">
		<param name="movie" value="${ctx}/js/dewplayer/dewplayer.swf" />
		<param name="flashvars"
			value="mp3=<c:out value="${content.audioRef}" ></c:out>" />
		<param name="wmode" value="transparent" />
	</object>
</div>

	<%-- <audio autoplay="autoplay" src="<c:out value="${content.audioRef}" ></c:out>" controls="controls"></audio> --%>
	<jsp:include page="../show-tags-box.jsp"></jsp:include>
	<div id='mediaspace'>This text will be replaced</div>

	<script type='text/javascript'>
		jwplayer('mediaspace').setup({
			'flashplayer' : '${ctx}/js/mediaplayer-5.9-viral/player.swf',
			'file' : '<c:out value="${content.audioRef}" ></c:out>',
			'controlbar' : 'bottom',
			'width' : '440',
			'height' : '260',
			'skin' : '${ctx}/js/video-js/skins/grungetape.zip',
			'image' : '${userPictureURI}${fn:toLowerCase(content.author.userName)}${userPictureExt}'
		});
	</script>


	<jsp:include page="../comments-box.jsp" />
</body>
</html>