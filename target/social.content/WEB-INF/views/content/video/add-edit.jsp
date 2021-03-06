<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Upload Example</title>
    </head>
    <body>
        <form:form action="${action}" modelAttribute="video" method="post"  enctype="multipart/form-data">
 
                <p>
                    <form:label for="title" path="title">Name</form:label><br/>
                    <form:input path="title"/>
                    <c:if test="${not empty errors}">
						<c:out value="${errors.title}"></c:out>
					</c:if>
                </p>
 
                <p>
                    <form:label for="fileData" path="fileData">File: <c:out value="${video.fileName}" /></form:label><br/>
                    <form:input path="fileData" type="file" />
                    
                </p>
                <p>
                    <form:label for="url" path="url">URL: <c:out value="${video.url}" /></form:label><br/>
                    <form:input path="url" type="text" />
                    
                </p>
 
                <p>
                    <input type="submit" />
                </p>
 
        </form:form>
    </body>
</html>
</body>
</html>