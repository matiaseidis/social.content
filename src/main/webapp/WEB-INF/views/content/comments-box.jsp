<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<%-- <%@ page session="false"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div>
	<h3>Comente esto viejo!</h3>
	<sec:authorize access="isAuthenticated()">
		<form:form
			action="${pageContext.request.contextPath}/comment/add/${contentType}/${content.id}"
			modelAttribute="comment" method="post">
				<form:label for="body" path="body"></form:label>
				<form:textarea cols="80" rows="4" path="body" />
			<p>
				<input type="submit" value="comentar"/>
			</p>

		</form:form>
	</sec:authorize>
	<c:forEach items="${content.comments}" var="c">
		<div class="comment">
			<myTags:userImg height="50" width="50" username="${c.author.userName}"></myTags:userImg>			
			<p>
				<c:out value="${c.body}"></c:out>
			</p>
			<h6>
				Comentado por <c:out value="${c.author.userName}"></c:out>, el
				<fmt:formatDate pattern="dd/MM/yyyy" dateStyle="short" type="date"
				value="${c.postDate}" timeZone="es" />
				
			</h6>
		</div>
	</c:forEach>
	<sec:authorize access="isAnonymous()">registrese si quiere commentar...</sec:authorize>
</div>
