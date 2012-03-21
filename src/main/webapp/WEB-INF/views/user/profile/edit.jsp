<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>






<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Editar perfil</title>
    </head>
    <body>
        <form:form action="${action}" modelAttribute="user" method="post"  enctype="multipart/form-data">
                
 				<c:if test="${not empty errors}">
 				<p>
						<c:out value="${errors.unavailable}"></c:out>
					</p>
					</c:if>
                <p>
                <c:out value="${user.userName}"></c:out>
                </p>
                <a href="passwordEdit">Cambiar contraseña</a> 
                <p>
                    <form:label for="email" path="email">email</form:label><br/>
                    <form:input path="email"/>
                    <myTags:errorField field="email"></myTags:errorField>
                </p>
                
                <p>
                    <form:label for="info" path="info">info</form:label><br/>
                    <form:textarea path="info"/>
                    
                </p>
                <p>
                
                    <form:label for="image" path="image">Image: 
                    </form:label><br/>
                    <form:input path="image" type="file" />
					<myTags:errorField field="image"></myTags:errorField>
					
					
                </p>
                <p>
                    <input type="submit" value="Actualizar perfil"/>
                </p>
 
        </form:form>
    </body>
</html>
</body>
</html>