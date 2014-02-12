<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	$(document).ready(function() {
		$("#searchSubmit").click(function(){
			$("#searchContent").find(".active form").submit();
		});
	});
</script>

<div class="page-header">
	<h1>Find books</h1>
</div>

<div class="bs-example bs-example-tabs">
	<ul class="nav nav-tabs" id="searchTab">
		<li class="active"><a data-toggle="tab" href="#search-simple">Simple</a></li>
		<li class=""><a data-toggle="tab" href="#search-extended">Extended</a></li>
		<li style="float: right"><button type="button" class="btn btn-primary" id="searchSubmit">Submit</button></li>
	</ul>
	<div class="tab-content" id="searchContent">
		<div id="search-simple" class="tab-pane fade active in">
			<form method="GET" action="${contextPath}/search">
				<div class="form-group">
					<label for="content-smpl">Book content</label>
					<input name="content" type="text" class="form-control" id="content-smpl" placeholder="Enter part of book text">
				</div>
			</form>
		</div>
		<div id="search-extended" class="tab-pane fade">
			<form method="GET" action="${contextPath}/search">
				<div class="row">
					<div class="col-md-7">
						<label for="content-ext">Book content</label>
						<input name="content" type="text" class="form-control" id="content-ext" placeholder="IT was a special pleasure to see things eaten">
					</div>
					<div class="col-md-5">
						<label for="title">Title</label>
						<input name="title" type="text" class="form-control" id="title" placeholder="Fahrenheit 451">
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<label for="author">Author</label>
						<input name="author" type="text" class="form-control" id="author" placeholder="Ray Bradbury">
					</div>
					<div class="col-md-4">
						<label for="genre">Genre</label>
						<input name="genre" type="text" class="form-control" id="genre" placeholder="comedy science fiction">
					</div>
					<div class="col-md-4">
						<label for="language">Genre</label>
						<input name="lang" type="text" class="form-control" id="language" placeholder="en ru ua">
					</div>
				</div>
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



