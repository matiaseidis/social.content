<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<myTags:paginatedContentList contentList="${paginatedContent}" page="${page}" title="updated via ajax"></myTags:paginatedContentList>