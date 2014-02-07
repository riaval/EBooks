<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<div class="page-header">
	<h1>Search</h1>
</div>

<form method="GET" action="${contextPath}/search">
	<div class="form-group">
		<label for="content">Book content</label>
		<input name="content" type="text" class="form-control" id="content" placeholder="Enter part of book text">
	</div>
	<button type="submit" class="btn btn-default">Submit</button>
</form>



