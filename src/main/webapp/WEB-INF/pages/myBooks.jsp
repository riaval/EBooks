<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

		<div class="page-header">
			<h1><spring:message code="mybooksTitle"/></h1>
		</div>

		<c:if test="${empty books}">
			<p class="lead"><spring:message code="nothingSoFar"/></p>
			<p >
				<spring:message code="emptyMessage.start"/>
				<a href="${contextPath}/upload"><spring:message code="emptyMessage.href"/></a>
				<spring:message code="emptyMessage.end"/>
			</p>
		</c:if>

		<c:forEach var="book" items="${books}">
			<c:set var="book" value="${book}" scope="request" />
			<jsp:include page="../templates/book-include.jsp" />
		</c:forEach>

		<c:set var="currentPathe" value="${contextPath}/mybooks" scope="request" />
		<jsp:include page="../templates/pagination-include.jsp" />

		<jsp:include page="../templates/users-modal-include.jsp" />
