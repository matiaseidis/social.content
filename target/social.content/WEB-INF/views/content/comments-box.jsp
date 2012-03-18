<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<%-- <%@ page session="false"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div>
			<h3>Comente esto viejo!</h3>
		<sec:authorize access="isAuthenticated()">
			<form:form action="${pageContext.request.contextPath}/comment/add/${contentType}/${content.id}" modelAttribute="comment"
				method="post">
				<p>
					<form:label for="body" path="body"></form:label>
					<br />
					<form:textarea cols="80" rows="4" path="body" />
				</p>

				<p>
					<input type="submit" />
				</p>

			</form:form>
		</sec:authorize>
		<c:forEach items="${content.comments}" var="c">
			<div class="comment">
			<h6>Por <c:out value="${c.author.userName}"></c:out></h6>
			<h6>el <c:out value="${c.postDate}"></c:out></h6>
			<h4><c:out value="${c.body}"></c:out></h4>
			</div>
		</c:forEach>
		<sec:authorize access="isAnonymous()">registrese si quiere commentar...</sec:authorize>
</div>
