<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="contentType" required="false" rtexprvalue="true"%>
<%@ attribute name="page" required="true" rtexprvalue="true"%>
<%@ attribute name="updatedTagId" required="true" rtexprvalue="true"%>
<%@ attribute name="contentList" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<%@ tag body-content="tagdependent"%>
<c:if test="${not empty contentList}">
<!-- 	<div id="paginatedContent"> -->
		<div>
			<h3>${title}</h3>
			<ul>
				<c:forEach var="content" items="${contentList}">
					<li><span> <myTags:userImg height="50" width="50"
								username="${content.author.userName}"></myTags:userImg>
					</span> <span> <a
							href="${ctx}/content/${content.contentType}/show/${content.id}">${content.title}</a>
					</span> - <span>publicado por <a
							href="${ctx}/profile/${content.author.userName}">${content.author.userName}</a>
					</span> <span>el <fmt:formatDate dateStyle="short" type="date"
								value="${content.postDate}" timeZone="es" />
					</span></li>
				</c:forEach>
			</ul>
		</div>
		<div class="pager">
			<span><a id="previous" href="${prevPage}">
			<c:if test="${prevPage gt -1}">
			<c:out value="<<"></c:out>
			</c:if>
			</a></span>
			<span><a id="next" href="${nextPage}">
			<c:if test="${nextPage gt 0}">
			<c:out value=">>"></c:out>
			</c:if>
			</a></span>
		</div>
<!-- 	</div> -->
</c:if>
<script>
$(function(){
	$('#previous').click(function(e){
		e.preventDefault();
		var prevPage = ${prevPage};
		if(prevPage == -1){
			return;
		}
		$.ajax({
	        url: '${ctx}/ajax/${updatedTagId}/'+prevPage+'/'+3,
// 	        data: ({name : "me"}),
	        success: function(data) {
	          
	        	$('#${updatedTagId}').html(data);
	        },
	        error: function(data) {
		          alert(data);
		        	$('#${updatedTagId}').html(data);
		        }
	      });
	});
	$('#next').click(function(e){
		e.preventDefault();
		var nextPage = ${nextPage};
		if(nextPage == 0){
			return;
		}
		var nextPage = $('#next').attr('href');
		$.ajax({
	        url: '${ctx}/ajax/${updatedTagId}/'+nextPage+'/'+3,
// 	        data: ({name : "me"}),
	        success: function(data) {
	          
	        	$('#${updatedTagId}').html(data);
	        },
	        error: function(data) {
		          alert(data);
		        	$('#${updatedTagId}').html(data);
		        }
	      });
	});
});
</script>