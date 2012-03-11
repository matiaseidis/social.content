<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Create account</title>
    </head>
    <body>
        <form:form action="${action}" modelAttribute="user" method="post"  enctype="multipart/form-data">
            <fieldset>
                <legend>Ingrese sus datos...</legend>
 				<c:if test="${not empty errors}">
 				<p>
						<c:out value="${errors.unavailable}"></c:out>
					</p>
					</c:if>
                <p>
                    <form:label for="userName" path="userName">Name</form:label><br/>
                    <form:input path="userName"/>
                    <c:if test="${not empty errors}">
						<c:out value="${errors.userName}"></c:out>
					</c:if>
                </p>
                <p>
                    <form:label for="password" path="password">Password</form:label><br/>
                    <form:password path="password"/>
                    <c:if test="${not empty errors}">
						<c:out value="${errors.password}"></c:out>
					</c:if>
                </p>
                
                
                    <form:label for="image" path="image">Image: </form:label><br/>
                    <form:input path="image" type="file" />
						<c:if test="${not empty errors}">
						<c:out value="${errors.image}"></c:out>
					</c:if>
					
                </p>
                <p>
                    <input type="submit" />
                </p>
 
            </fieldset>
        </form:form>
    </body>
</html>
</body>
</html>