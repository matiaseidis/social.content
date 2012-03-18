<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<%-- <%@ page session="false"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<div>
			<form:form action="${ctx}/tag/add/${content.id}" modelAttribute="tag"
				method="post">
				<p>
					<form:label for="tagName" path="tagName">etiquetar</form:label>
					<form:input path="tagName" />
					<c:if test="${not empty errors}">
						<c:out value="${errors.tagName}"></c:out>
					</c:if>
					<input type="submit" />
				</p>
			</form:form>
</div>
