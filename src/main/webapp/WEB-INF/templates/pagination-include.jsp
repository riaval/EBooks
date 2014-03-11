<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

		<c:if test="${totalPages > 1}">
			<ul class="pagination">
				<li class="${pageNumber == 1 ? 'disabled' : ''}">
					<a href="${currentPath}?page=${pageNumber - 1}">&laquo;</a>
				</li>
				<c:forEach begin="1" end="${totalPages}" varStatus="status">
					<li class="${pageNumber eq status.index ? 'active' : ''}">
						<a href="${currentPath}?page=${status.index}">${status.index}</a>
					</li>
				</c:forEach>
				<li class="${pageNumber == totalPages ? 'disabled' : ''}">
					<a href="${currentPath}?page=${pageNumber + 1}">&raquo;</a>
				</li>
			</ul>
		</c:if>