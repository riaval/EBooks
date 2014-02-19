<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div id="contextPath">${contextPath}</div>
<script src="<spring:url value="/js/book.js" />" type="text/javascript"></script>

        <!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel"><spring:message code="shareTitle"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="shareEmail"><spring:message code="addUser"/></label>
							<input name="shareEmail" type="email" required="true" placeholder="<spring:message code="addUserPlaceholder"/>" class="form-control" id="shareEmail">
						</div>
						<div id="sharedUsers">
							<table class="table table-hover" id="sharedUsersTable">
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="closeBt"/></button>
						<button type="button" class="btn btn-primary" id="share"><spring:message code="shareBt"/></button>
					</div>
				</div>
			</div>
		</div>