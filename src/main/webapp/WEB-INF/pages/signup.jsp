<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="" method="POST" class="form-signup">
	<h2 class="form-signin-heading">Please sign up</h2>

	<div class="form-group">
		<label for="email">Email address</label>
		<input name="email" type="email" autofocus="" required="true" placeholder="Email address" class="form-control" id="email">
	</div>
	<div class="form-group">
		<label for="firstName">First name</label>
		<input name="firstName" type="text" required="true" placeholder="First name" class="form-control" id="firstName">
	</div>
	<div class="form-group">
		<label for="lastName">Last name</label>
		<input name="lastName" type="text" required="true" placeholder="Last name" class="form-control" id="lastName">
	</div>
	<div class="form-group">
		<label for="password">Password</label>
		<input name="password" type="password" required="true" placeholder="Password" class="form-control" id="password">
	</div>
	<div class="form-group">
		<input name="passwordAgain" type="password" required="true" placeholder="Password (again)" class="form-control">
	</div>

	<button type="submit" class="btn btn-lg btn-primary btn-block">Sign up</button>

</form>