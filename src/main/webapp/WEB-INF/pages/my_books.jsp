<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div class="page-header">
			<h1>My books</h1>
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

				<div>
					<strong>Download: </strong><a href="${contextPath}/file/${book.id}">
					${book.extension} (<fmt:formatNumber type="number" maxFractionDigits="0" value="${book.size / 1024}" />Kb)
				</a>
				</div>

				<p>
					<div class="btn-group">
						<%--<input type="checkbox">--%>
						<button type="button" class="btn btn-success btn-xs">Share</button>
						<button type="button" class="btn btn-danger btn-xs">Delete</button>
					</div>
				</p>

			</div>
		</div>
		<!-- .row -->
		<hr>
		</c:forEach>