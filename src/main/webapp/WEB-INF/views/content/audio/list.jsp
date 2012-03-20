<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Audios:</p>
	<div>
		<table>
			<tbody>
				<c:forEach items="${audios}" var="audio">
					<tr>
						<td><c:out value="${audio.title}"></c:out></td>
						<td><span><a
								href="<c:out value="show/${audio.id}"></c:out>">ver</a></span></td>
						<td><span><a
								href="edit/<c:out value="${audio.id}"></c:out>">editar</a></span></td>
						<td>
							<form action="delete/<c:out value='${audio.id}'></c:out>"
								method="POST">
								<input type="submit" value="borrar">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>