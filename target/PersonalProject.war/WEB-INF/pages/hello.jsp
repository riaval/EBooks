<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <table>
        <tr>
            <th>No.</th>
            <th>Email</th>
            <th>Full Name</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.fullName}</td>
                <td>${user.role.role}</td>
            </tr>
        </c:forEach>
    </table>
    <form method="post">
        <input type="text" name="email" placeholder="Email" />
        <input type="text" name="fullName" placeholder="Full name" />
        <select name="role">
            <option>admin</option>
            <option>user</option>
        </select>
        <input type="submit" value="Submit"  />
    </form>
</body>
</html>