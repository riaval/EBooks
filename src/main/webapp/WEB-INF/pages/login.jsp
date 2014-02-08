<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<form name="f" action="<c:url value='j_spring_security_check' />" method="POST" class="form-signin">
			<h2 class="form-signin-heading">Please log in</h2>
			<input type="text" autofocus="" required="true" placeholder="Email address" class="form-control" name="j_username">
			<input type="password" required="true" placeholder="Password" class="form-control" name="j_password">
			<%--<label class="checkbox">--%>
			<%--<input type="checkbox" value="remember-me"> Remember me--%>
			<%--</label>--%>
			<button type="submit" class="btn btn-lg btn-primary btn-block">Log in</button>
		</form>