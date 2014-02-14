<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
	$(document).ready(function() {
		$("#searchSubmit").click(function(){
			$("#searchContent").find(".active form").submit();
		});
	});
</script>

<div class="page-header">
	<h1><spring:message code="searchTitle"/></h1>
</div>

<div class="bs-example bs-example-tabs">
	<ul class="nav nav-tabs" id="searchTab">
		<li class="${param.searchType == 'extended' ? '' : 'active'}"><a data-toggle="tab" href="#search-simple"><spring:message code="simple"/></a></li>
		<li class="${param.searchType == 'extended' ? 'active' : ''}"><a data-toggle="tab" href="#search-extended"><spring:message code="extended"/></a></li>
		<li style="float: right"><button type="button" class="btn btn-primary" id="searchSubmit"><spring:message code="searchBt"/></button></li>
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
		</div>
	</div>
</div>

<c:forEach var="book" items="${books}">
	<div class="row">
		<!-- title row -->
		<h2 class="book-title">
			<img height="18px" width="35px" src="<c:url value="/img/book.png" />">
			<a href="${contextPath}/book/${book.id}">${book.title}</a>
		</h2> <!-- show title as link to book -->
		<div class="contentholder col-md-9">
			<p>${book.annotation}</p>
		</div>
		<div class="metarea col-md-3">
			<!-- author info area -->
			<p class="book-author">
				<!-- show pen icon -->
				<img height="17px" width="17px" src="<c:url value="/img/pen.png" />" alt="">
				<!-- show author name as link -->
				<a href="http://english-e-books.net/author/richard-curtis/" title="All books by Richard Curtis"
				   rel="author">${book.author}</a>
			</p>

			<div>
				<strong>Language: </strong><a href="#">${book.language}</a>
			</div>
			<div>
				<strong>Genres: </strong>
				<c:forEach var="genre" items="${book.genres}">
					<a href="#">${genre}</a>
				</c:forEach>
			</div>
			<p>
				<strong>IBSN: </strong><a href="#">${book.isbn}</a>
			</p>

			<p>
				<strong>Download: </strong><a href="${contextPath}/file/${book.id}">
					${book.extension} (<fmt:formatNumber type="number" maxFractionDigits="0" value="${book.size / 1024}" />Kb)
			</a>
			</p>

			<div>
				<strong>Owner: </strong>${book.owner}
			</div>

		</div>
	</div>
	<!-- .row -->
	<hr>
</c:forEach>



