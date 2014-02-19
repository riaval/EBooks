<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="selections" value="${readingBook.content}" />

<div class="page-header">
	<h1>${readingBook.title}</h1>
</div>

<c:forEach begin="0" end="${selections.length - 1}" varStatus="loop">
<c:set var="contents" scope="page" value="${selections.item(loop.index).childNodes}"/>
<div class="selection">

	<c:forEach begin="0" end="${contents.length - 1}" varStatus="loop2">
	<c:set var="content" scope="page" value="${contents.item(loop2.index)}"/>

		<c:choose>
			<c:when test="${content.nodeName == 'title'}">
				<h3>${content.textContent}</h3>
			</c:when>
			<c:when test="${content.nodeName == 'p'}">
				<p>${content.textContent}</p>
			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>

	</c:forEach>

</div>
</c:forEach>




