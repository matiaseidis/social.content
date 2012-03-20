<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>
	<c:if test="${not empty param.login_error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
 
		<table>
			<tr>
<!-- 				<td>User:</td> -->
				<td><label for="j_username" path="j_username">Usuario: </label>
<!-- 				</td> -->
<!-- 				<td> -->
				<input type='text' name='j_username' value='' class="fixed-height"></td>
<!-- 				<td>Password:</td> -->
				<td><label for="j_password" path="j_password">&nbsp&nbspContraseña: </label>
<!-- 				<td> -->
				<input type='password' name='j_password' class="fixed-height"/>
				</td>
				<td colspan='2'>&nbsp&nbsp<input name="submit" type="submit"
					value="entrar" class="button"/>
				</td>
<!-- 				<td colspan='2'><input name="reset" type="reset" /> -->
<!-- 				</td> -->
			</tr>
		</table>
 
	</form>
	<a href="register">Crear cuenta</a>
</body>
</html>

	<form:label for="title" path="title">Título</form:label>
	<br />
	<form:input path="title" class="long fixed-height" />