<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
	<link href="<c:url value="/css/main.css" />" rel="stylesheet">
	<!-- jQuery -->
	<script type="text/javascript" charset="UTF-8" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<!-- Bootstrap -->
	<link href="<c:url value="/plugins/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/plugins/bootstrap/js/bootstrap.min.js" />"></script>
	<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<!-- Wrap all page content here -->
<div id="wrap">

	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${contextPath}">EBooks</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="<tiles:insertAttribute name="search" />"><a href="${contextPath}/search">Search</a></li>
					<sec:authorize access="isAuthenticated()">
					<li class="<tiles:insertAttribute name="upload" />"><a href="${contextPath}/upload">Upload</a></li>
					<li class="<tiles:insertAttribute name="myBooks" />"><a href="${contextPath}/mybooks">My books</a></li>
					<li class="<tiles:insertAttribute name="jobs" />"><a href="${contextPath}/jobs">Jobs</a></li>
					<li class="<tiles:insertAttribute name="shared" />"><a href="${contextPath}/shared">Shared</a></li>
					</sec:authorize>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="isAnonymous()">
						<li><a href="${contextPath}/login">Log in</a></li>
						<li><a href="${contextPath}/signup">Sign up</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><sec:authentication property="principal.username" /><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Profile</a></li>
								<li><a href="<c:url value="/j_spring_security_logout" />">Log out</a></li>
							</ul>
						</li>
					</sec:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<!-- Begin page content -->
	<div class="container">
		<tiles:insertAttribute name="content" />
	</div>

</div>

<div id="footer">
	<div class="container">
		<p class="text-muted">Copyright © Miratech 2014</p>
	</div>
</div>

</body>
</html>