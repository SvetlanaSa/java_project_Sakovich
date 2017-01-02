<%--
  Created by IntelliJ IDEA.
  User: Света
  Date: 22.11.2016
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Show All Account</title>
</head>
<body>
<thead>
<tr>
    <th>Acccount ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Country</th>
    <th colspan="2">Action</th>
</tr>
</thead>
<tbody>
<c:forEach items="${accounts}" var="account">
    <tr>
        <td><c:out value="${account.Account_ID}" /></td>
        <td><c:out value="${account.FirstName}" /></td>
        <td><c:out value="${account.LastName}" /></td>
        <td><c:out value="${account.Country}" /></td>
        <td><a
                href="AccountController.do?action=edit&Account_ID=<c:out value="${account.Account_ID}"/>">Update</a></td>
        <td><a
                href="AccountController.do?action=delete&Account_ID=<c:out value="${account.Account_ID}"/>">Delete</a></td>
            </tr>
</c:forEach>
</tbody>
</table>
<p>
    <a href="AccountController.do?action=insert">Add Account</a>
</p>
</body>
</html>
