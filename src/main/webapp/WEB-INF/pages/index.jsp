<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<div class="page-header">
			<h1><spring:message code="lastbookTitle"/></h1>
		</div>

		<c:forEach var="book" items="${books}">
			<c:set var="book" value="${book}" scope="request" />
			<jsp:include page="../templates/book-include.jsp" />
		</c:forEach>