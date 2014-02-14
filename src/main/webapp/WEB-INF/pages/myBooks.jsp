<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div class="page-header">
			<h1><spring:message code="mybooksTitle"/></h1>
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
			<c:set var="book" value="${book}" scope="request" />
			<jsp:include page="../templates/book-include.jsp" />
		</c:forEach>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel"><spring:message code="shareTitle"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="shareEmail"><spring:message code="addUser"/></label>
							<input name="shareEmail" type="email" required="true" placeholder="<spring:message code="addUserPlaceholder"/>" class="form-control" id="shareEmail">
						</div>
						<div id="sharedUsers">
							<table class="table table-hover" id="sharedUsersTable">
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="closeBt"/></button>
						<button type="button" class="btn btn-primary" id="share"><spring:message code="shareBt"/></button>
					</div>
				</div>
			</div>
		</div>