	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
	<myTags:contentList contentList="${content}" title="Content" contentType="content"></myTags:contentList>
	<myTags:contentList contentList="${videos}" title="Videos" contentType="video"></myTags:contentList>
	<myTags:contentList contentList="${audios}" title="Audios" contentType="audio"></myTags:contentList>
	<myTags:contentList contentList="${posts}" title="Posts" contentType="post"></myTags:contentList>
	<myTags:contentList contentList="${events}" title="Eventos" contentType="event"></myTags:contentList>
	