<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${video.title}</title>
</head>
<body>
	<div>
	<p>
				<h1>${video.title}</h1>
				</p>
				<p>
				<h4>${video.uri}</h4>
				</p>
	</div>
	<video id="my_video_1" class="video-js vjs-default-skin" controls
  preload="auto" width="640" height="264" poster="my_video_poster.png"
  data-setup="{}">
  <source src="${video.uri}" type='video/flv'>
<!--   <source src="my_video.mp4" type='video/mp4'> -->
<!--   <source src="my_video.webm" type='video/webm'> -->
</video>

	
<link href="http://vjs.zencdn.net/c/video-js.css" rel="stylesheet">
<script src="http://vjs.zencdn.net/c/video.js"></script>

	
</body>
</html>