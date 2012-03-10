<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<%-- <%@ page session="false"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div>
	<fieldset>
		<legend>
			<h3>Comente esto viejo!</h3>
		</legend>
		<sec:authorize access="isAuthenticated()">
			<form:form action="comment/add/" modelAttribute="comment"
				method="post">
				<p>
					<form:label for="title" path="title">Titulo</form:label>
					<br />
					<form:input path="title" />
					<c:if test="${not empty errors}">
						<c:out value="${errors.title}"></c:out>
					</c:if>
				</p>
				<p>
					<form:label for="body" path="body"></form:label>
					<br />
					<form:textarea cols="80" rows="10" path="body" />
					<c:if test="${not empty errors}">
						<c:out value="${errors.body}"></c:out>
					</c:if>
				</p>

				<p>
					<input type="submit" />
				</p>

			</form:form>
		</sec:authorize>
		<sec:authorize access="isAnonymous()">invitacion a autenticarse / registrarse</sec:authorize>
	</fieldset>
</div>
