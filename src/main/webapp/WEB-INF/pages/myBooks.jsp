<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div class="page-header">
			<h1>My books</h1>
		</div>
<script>
	function sharedType(iBookId, sType) {
		$.ajax({
			type: "POST",
			url: "${contextPath}/book/"+iBookId,
			data: {
				'type': sType
			},
			success: function (data, text) {
				$.growl.notice({ message: "Changes saved successfully" });
			},
			error: function (request, status, error) {
				$.growl.error({ message: error });
			}
		});
	}

	function share(iBookId) {
		$('#myModal').modal();
		var users = getSharedUsers(iBookId);
		$('#share').click(function() {
			var sEmail = $( "#shareEmail").val();
			$.ajax({
				type: "POST",
				url: "${contextPath}/book/share/"+iBookId,
				data: {
					'email': sEmail
				},
				success: function (data, text) {
					$('#shareEmail').val('');
					$('#myModal').modal('hide');
					$.growl.notice({ message: "Changes saved successfully" });
				},
				error: function (request, status, error) {
					$.growl.error({ message: error });
				}
			});
			$(this).unbind('click');
		});
	}

	function getSharedUsers(iBookId) {
		var tableBody = $('#sharedUsersTable').find('tbody');
		tableBody.empty();
		$.ajax({
			type: 'GET',
			url: '${contextPath}/book/' + iBookId + '/users',
			success: function (data) {
				$.each(data, function(index, user) {
					tableBody.append($('<tr>')
						.append('<td>' + user.firstName + ' ' + user.lastName + '</td>' +
									'<td>' + user.email + '</td>' +
									'<td><span class="rm-user glyphicon glyphicon-remove" onclick="unShare(this, ' + iBookId + ', '+ user.id +')"></span></td>'));
				});
			}
		});
	}

	function unShare(event, iBookId, iUserId) {
		var tableBody = $('#sharedUsersTable').find('tbody');
//		var thisElement = this;
		$.ajax({
			type: 'POST',
			url: '${contextPath}/books/' + iBookId + '/users/' + iUserId + '/delete',
			success: function (data) {
				$(event).parent().parent().remove();
				$.growl.notice({ message: "Successfully deleted" });
			}
		});
	}
</script>
		<c:forEach var="book" items="${books}">
		<div class="row">
			<!-- title row -->
			<h2 class="book-title my-book-title">
				<div class="title-content">
					<img height="18px" width="35px" src="<c:url value="/img/book.png" />">
					<a href="${contextPath}/book/${book.id}">${book.title}</a>
				</div>
				<div class="btn-group shared-block" data-toggle="buttons">
					<%--<form action="${contextPath}/book/type/${book.id}" method="GET">--%>
						<label class="btn btn-primary ${book.sharedType == 'PRIVATE' ? 'active' : ''}" onclick="sharedType(${book.id}, 'private')">
							<input type="radio"> Private
						</label>
						<label class="btn btn-primary ${book.sharedType == 'PUBLIC' ? 'active' : ''}" onclick="sharedType(${book.id}, 'public')">
							<input type="radio"> Public
						</label>
					<%--</form>--%>
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
						<%--<a href="#" type="button" class="btn btn-success btn-xs">Share</a>--%>
						<button class="btn btn-success btn-xs" onclick="share(${book.id})">Share</button>
						<a href="${contextPath}/book/delete/${book.id}" type="button" class="btn btn-danger btn-xs">Delete</a>
					</div>
				</p>

			</div>
		</div>
		<!-- .row -->
		<hr>
		</c:forEach>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Share book</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="shareEmail">Add user</label>
							<input name="shareEmail" type="email" required="true" placeholder="Enter user email" class="form-control" id="shareEmail">
						</div>
						<div id="sharedUsers">
							<table class="table table-hover" id="sharedUsersTable">
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" id="share">Share</button>
					</div>
				</div>
			</div>
		</div>