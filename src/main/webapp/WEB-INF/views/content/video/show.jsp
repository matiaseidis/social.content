<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type='text/javascript' src='../../js/mediaplayer-5.9-viral/jwplayer.js'></script>	

<title>${video.title}</title>
</head>
<body>
<h1><c:out value="${video.title}" ></c:out></h1>

<div id='mediaspace'>This text will be replaced</div>

<script type='text/javascript'>
  jwplayer('mediaspace').setup({
    'flashplayer': '../../js/mediaplayer-5.9-viral/player.swf',
    'file': '<c:out value="${video.baseUrl}${video.fileName}" ></c:out>',
    'controlbar': 'bottom',
    'width': '470',
    'height': '320'
  });
</script>

	
</body>
</html>