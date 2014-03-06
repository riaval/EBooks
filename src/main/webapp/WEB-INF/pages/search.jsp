<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="user" property="principal.username" />
</sec:authorize>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
	$(document).ready(function() {
		$("#searchSubmit").click(function(){
			$("#searchContent").find(".active form").submit();
		});

		$("#searchClear").click(function() {
			$('form').find("input[type=text], textarea").val("");
		});

		$("form input").keydown(function(e) {
			if (e.keyCode == 13) {
				$('form').submit();
			}
		});
	});
</script>

<div class="page-header">
	<h1><spring:message code="searchTitle"/></h1>
</div>

<div class="search-tabs">
	<ul class="nav nav-tabs" id="searchTab">
		<li class="${param.searchType == 'extended' ? '' : 'active'}"><a data-toggle="tab" href="#search-simple"><spring:message code="simple"/></a></li>
		<li class="${param.searchType == 'extended' ? 'active' : ''}"><a data-toggle="tab" href="#search-extended"><spring:message code="extended"/></a></li>
		<li style="float: right"><button type="button" class="btn btn-primary" id="searchSubmit"><spring:message code="searchBt"/></button></li>
		<li style="float: right"><button type="button" class="btn btn-default" id="searchClear"><spring:message code="clear"/></button></li>
	</ul>
	<div class="tab-content" id="searchContent">
		<div id="search-simple" class="tab-pane fade ${param.searchType == 'extended' ? '' : 'active in'}">
			<form method="GET" action="${contextPath}/search">
				<div class="form-group">
					<label for="content-smpl"><spring:message code="bookContent"/></label>
					<input name="content" type="text" class="form-control" id="content-smpl" placeholder="<spring:message code="bookContentPlaceholder"/>" value="${param.searchType == 'simple' ? fn:escapeXml(param.content) : ''}">
				</div>
				<input name="searchType" type="hidden" value="simple">
			</form>

			<c:if test="${param.searchType == 'simple'}">
				<div class="search-result">
					<c:if test="${empty books}">
						<p class="lead"><spring:message code="nothingFound"/></p>
					</c:if>

					<c:forEach var="book" items="${books}">
						<c:set var="book" value="${book}" scope="request" />
						<jsp:include page="../templates/book-include.jsp" />
					</c:forEach>
				</div>
			</c:if>
		</div>
		<div id="search-extended" class="tab-pane fade ${param.searchType == 'extended' ? 'active in' : ''}">
			<form method="GET" action="${contextPath}/search">
				<div class="row">
					<div class="col-md-7">
						<label for="content-ext"><spring:message code="bookContent"/></label>
						<input name="content" type="text" class="form-control" id="content-ext" placeholder="<spring:message code="bookContentPlaceholder"/>" value="${param.searchType == 'extended' ? fn:escapeXml(param.content) : ''}">
					</div>
					<div class="col-md-5">
						<label for="title"><spring:message code="title"/></label>
						<input name="title" type="text" class="form-control" id="title" placeholder="<spring:message code="titlePlaceholder"/>" value="${fn:escapeXml(param.title)}">
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<label for="author"><spring:message code="author"/></label>
						<input name="author" type="text" class="form-control" id="author" placeholder="<spring:message code="authorPlaceholder"/>" value="${fn:escapeXml(param.author)}">
					</div>
					<div class="col-md-4">
						<label for="genre"><spring:message code="genre"/></label>
						<input name="genre" type="text" class="form-control" id="genre" placeholder="<spring:message code="genrePlaceholder"/>" value="${fn:escapeXml(param.genre)}">
					</div>
					<div class="col-md-4">
						<label for="language"><spring:message code="language"/></label>
						<input name="language" type="text" class="form-control" id="language" placeholder="<spring:message code="languagePlaceholder"/>" value="${fn:escapeXml(param.language)}">
					</div>
				</div>
				<input name="searchType" type="hidden" value="extended">
			</form>

			<c:if test="${param.searchType == 'extended'}">
			<div class="search-result">
				<c:if test="${empty books}">
					<p class="lead"><spring:message code="nothingFound"/></p>
				</c:if>

				<c:forEach var="book" items="${books}">
					<c:set var="book" value="${book}" scope="request" />
					<jsp:include page="../templates/book-include.jsp" />
				</c:forEach>
			</div>
			</c:if>
		</div>
	</div>
</div>

<jsp:include page="../templates/users-modal-include.jsp" />



