<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script>
	$( document ).ready(function() {
		var frm = $('#form');
		frm.submit(function (ev) {
			$.ajax({
				type: frm.attr('method'),
				url: frm.attr('action'),
				data: frm.serialize(),
				success: function () {
					$.growl.notice({ message: "Changes saved successfully" });
				},
				error: function (request, status, error) {
					$.growl.error({ message: error });
				}
			});

			ev.preventDefault();
		});

		$( "#confirm" ).click(function() {
			frm.submit();
		});
	});
</script>

		<div class="page-header">
			<div class="row">
				<div class="col-md-10">
					<h1>Edit book</h1>
				</div>
				<div class="col-md-2">
					<div class="right-title">
						<button id="confirm" type="button" class="btn btn-primary">Confirm</button>
					</div>
				</div>
			</div>
		</div>

		<form id="form" method="POST" action="${contextPath}/book/${book.id}/edit">
			<div class="form-group">
				<label for="title">Title</label>
				<input value="${book.title}" name="title" type="text" placeholder="Fahrenheit 451" class="form-control" id="title">
			</div>

			<div class="form-group">
				<label for="author">Author</label>
				<input value="${book.author}" name="author" type="text" placeholder="Ray Bradbury" class="form-control" id="author">
			</div>

			<div class="form-group">
				<label for="isbn">ISBN</label>
				<input value="${book.isbn}" name="isbn" type="text" placeholder="5-9713-1191-3" class="form-control" id="isbn">
			</div>

			<div class="form-group">
				<label for="language">Language</label>
				<input value="${book.language}" name="language" type="text" placeholder="en" class="form-control" id="language">
			</div>

			<div class="form-group">
				<label for="genres">Genres</label>
				<%--<c:forEach var='genre' items='${book.genres}'>${genre}</c:forEach>--%>
				<input value="<c:forEach var='genre' items='${book.genres}'>${genre} </c:forEach>" name="genres" type="text" placeholder="comedy science fiction" class="form-control" id="genres">
			</div>

			<div class="form-group">
				<label for="annotation">Annotation</label>
				<textarea name="annotation" class="form-control" rows="8" id="annotation">${book.annotation}</textarea>
			</div>
		</form>