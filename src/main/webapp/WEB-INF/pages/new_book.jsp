<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="style.css" rel="stylesheet">
    <!-- jQuery -->
    <script type="text/javascript" charset="UTF-8" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" charset="UTF-8" src="bootstrap/js/bootstrap.min.js"></script>
    <title>BookHub — Main page</title>
</head>
<body>
<!-- Static navbar -->
<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">BookHub</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">Search</a></li>
                <li class="active"><a href="#">My books</a></li>
                <li><a href="#">Shared books</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <!--<li><a href="#">Login</a></li>-->
                <!--<li><a href="#">Sign up</a></li>-->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dmitry Zhukov<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Options</a></li>
                        <li><a href="#">Log out</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>


<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1>${message}</h1>

        <table>
            <tr>
                <th>No.</th>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.role.name}</td>
                </tr>
            </c:forEach>
        </table>

        <p>This example is a quick exercise to illustrate how the default, static and fixed to top navbar work. It
            includes the responsive CSS and HTML, so it also adapts to your viewport and device.</p>

        <p>To see the difference between static and fixed top navbars, just scroll.</p>

        <p>
            <a class="btn btn-lg btn-primary" href="../../components/#navbar" role="button">View navbar docs &raquo;</a>
        </p>
    </div>

</div>
<!-- /container -->

<!-- 	<div class="expanded">
		<nav class="navbar navbar-fixed-top">

		</nav>

		<div class="wrapper top">
			<ui:insert name="content" />
		</div>
		
		<div class="push"></div>
	</div>

	<footer>
		<div class="wrapper">
			<p class="muted credit">© #{res.author} 2013</p>
		</div>
	</footer> -->

</body>
</html>