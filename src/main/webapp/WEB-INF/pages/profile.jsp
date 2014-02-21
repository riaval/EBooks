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
			<h1>Profile</h1>
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
		<label for="email">Email address</label>
		<input name="email" type="email" autofocus="" required="true" disabled="disabled" class="form-control" id="email">
	</div>
	<div class="form-group">
		<label for="firstName">First name</label>
		<input name="firstName" type="text" required="true" placeholder="John" class="form-control" id="firstName">
	</div>
	<div class="form-group">
		<label for="lastName">Last name</label>
		<input name="lastName" type="text" required="true" placeholder="Doe" class="form-control" id="lastName">
	</div>

	<div class="form-group">
		<label for="opdPassword">Old password</label>
		<input name="oldPassword" type="password" required="true" placeholder="Old password" class="form-control" id="opdPassword">
	</div>

	<div class="form-group">
		<label for="password">New password</label>
		<input name="newPassword" type="password" required="true" placeholder="New password" class="form-control" id="password">
	</div>
	<div class="form-group">
		<input name="newPasswordAgain" type="password" required="true" placeholder="New password (again)" class="form-control">
	</div>
</form>
