<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${totalPages > 1}">
    <ul class="pagination">
        <li class="${pageNumber == 1 ? 'disabled' : ''}">
            <a href="${currentPath}${pageNumber - 1}?${currentParameters}">&laquo;</a>
        </li>
        <c:forEach begin="1" end="${totalPages}" varStatus="status">
            <li class="${pageNumber eq status.index ? 'active' : ''}">
                <a href="${currentPath}${status.index}?${currentParameters}">${status.index}</a>
            </li>
        </c:forEach>
        <li class="${pageNumber == totalPages ? 'disabled' : ''}">
            <a href="${currentPath}${pageNumber + 1}?${currentParameters}">&raquo;</a>
        </li>
    </ul>
</c:if>