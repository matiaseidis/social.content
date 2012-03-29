<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="hasOwnImage" required="true" rtexprvalue="true"%>
<%@ attribute name="username" required="true" rtexprvalue="true"%>
<%@ attribute name="width" required="true" rtexprvalue="true"%>
<%@ attribute name="height" required="true" rtexprvalue="true"%>


<img
	src="${userPictureURI}
<c:choose>
<c:when test="${hasOwnImage}">${fn:toLowerCase(username)}</c:when>
<c:otherwise>${userDefaultImgFileName}</c:otherwise>
</c:choose>
${userPictureExt}"
	width="${width}px" height="${height}px" />
