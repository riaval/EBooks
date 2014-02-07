<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="page-header">
	<h1>Jobs</h1>
</div>

<table class="table table-hover">
	<thead>
	<tr>
		<th>#</th>
		<th>Action</th>
		<th>Start</th>
		<th>Finish</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="job" items="${jobs}">
	<tr>
		<td>${job.id}</td>
		<td>${job.comment}</td>
		<td><fmt:formatDate pattern="dd.MM.yyyy, HH:mm:ss" value="${job.startTime}" /></td>
		<td><fmt:formatDate pattern="dd.MM.yyyy, HH:mm:ss" value="${job.endTime}" /></td>
	</tr>
	</c:forEach>
	</tbody>
</table>