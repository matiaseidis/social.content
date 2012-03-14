<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="username" required="true" rtexprvalue="true"%>
<%@ attribute name="width" required="true" rtexprvalue="true"%>
<%@ attribute name="height" required="true" rtexprvalue="true"%>
<img src="${userPictureURI}${fn:toLowerCase(username)}${userPictureExt}" width="${width}px" height="${height}px" />
