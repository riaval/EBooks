<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="user" property="principal.username" />
</sec:authorize>

<div class="row">
	<!-- title row -->
	<h2 class="book-title my-book-title">
		<div class="title-content col-md-9">
			<img height="18px" width="35px" src="<c:url value="/img/book.png" />">
			<a href="${contextPath}/book/${book.id}">${book.title}</a>
		</div>
		<div class="col-md-3">
			<c:if test="${user eq book.owner}">
				<div class="btn-group" data-toggle="buttons">
					<label class="btn btn-primary ${book.sharedType == 'PRIVATE' ? 'active' : ''}" onclick="sharedType(${book.id}, 'private')">
						<input type="radio"> <spring:message code="private"/>
					</label>
					<label class="btn btn-primary ${book.sharedType == 'PUBLIC' ? 'active' : ''}" onclick="sharedType(${book.id}, 'public')">
						<input type="radio"> <spring:message code="public"/>
					</label>
				</div>
			</c:if>
		</div>

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
			<a href="${contextPath}/search?content=&title=&author=${book.author}&genre=&language=&searchType=extended"
			   title="All books by Richard Curtis"
			   rel="author">${book.author}</a>
		</p>

		<div>
			<strong><spring:message code="language"/>: </strong>
			<a href="${contextPath}/search?content=&title=&author=&genre=&language=${book.language}&searchType=extended">${book.language}</a>
		</div>
		<div>
			<strong><spring:message code="genres"/>: </strong>
			<c:forEach var="genre" items="${book.genres}">
				<a href="${contextPath}/search?content=&title=&author=&genre=${genre}&language=&searchType=extended">${genre}</a>
			</c:forEach>
		</div>
		<p>
			<strong>IBSN: </strong>${book.isbn}
		</p>

		<div>
			<strong><spring:message code="download"/>: </strong><a href="${contextPath}/file/${book.id}">
			${book.extension} (<fmt:formatNumber type="number" maxFractionDigits="0" value="${book.size / 1024}" />Kb)
		</a>
		</div>

		<p>

		<c:if test="${user eq book.owner}">
		<div class="btn-group">
			<button class="btn btn-success btn-xs" onclick="share(${book.id})"><spring:message code="share"/></button>
			<a href="${contextPath}/book/${book.id}/edit" type="button" class="btn btn-warning btn-xs">&nbsp;Edit&nbsp;</a>
			<a href="${contextPath}/book/delete/${book.id}" type="button" class="btn btn-danger btn-xs"><spring:message code="delete"/></a>
		</div>
		</c:if>

	</div>
</div>
<!-- .row -->
<hr>