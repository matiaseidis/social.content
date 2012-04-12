<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <p>Body</p> -->
<textarea rows="20" cols="70" name="body">
			<c:choose>
				<c:when test="${not empty errors}">
					<c:out value="${errors.content.body}"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="${content.body}"></c:out>
				</c:otherwise>
			</c:choose>
		</textarea>

