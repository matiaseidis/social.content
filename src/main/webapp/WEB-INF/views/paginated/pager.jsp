<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pager">
	<span><a id="previous" href="${prevPage}"> <c:if
				test="${prevPage gt -1}">
				<c:out value="<<"></c:out>
			</c:if>
	</a></span> <span><a id="next" href="${nextPage}"> <c:if
				test="${nextPage gt 0}">
				<c:out value=">>"></c:out>
			</c:if>
	</a></span>
</div>