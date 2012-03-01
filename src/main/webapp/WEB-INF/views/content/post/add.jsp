<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>Create Post</title>
</head>
<body>
<%-- <form action="create" method=POST> --%>
<!-- <input type="text" name="title"> -->
<!-- <p></p> -->
<!-- <textarea rows="20" cols="70" name="body"></textarea> -->
<!-- <p></p> -->
<!-- <input type="submit"> -->
<%-- </form> --%>


<form:form method="post" action="create" commandName="command">

<table>

<tr>
<td>Title:<font color="red"><form:errors path="title" /></font></td>
</tr>

<tr>
<td><form:input path="title" /></td>
</tr>

<tr>
<td>Body:<font color="red"><form:errors path="body" /></font></td>
</tr>

<tr>
<td><form:input path="body" /></td>
</tr>





<tr>

<td><input type="submit" value="Submit" /></td>

</tr>

</table>

</form:form>

</body>
</html>