<%--
  Created by IntelliJ IDEA.
  User: Света
  Date: 27.11.2016
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Register new Account</title>
</head>/**/
<body>
<form action="/AccountController.do" method="post">
    <fieldset>

              <div>
            <label for="FirstName">First Name</label> <input type="text"
                                                             name="FirstName" value="<c:out value="${account.FirstName}" />"
                                                             placeholder="FirstName" />
        </div>
        <div>
            <label for="LastName">Last Name</label> <input type="text"
                                                           name="LastName" value="<c:out value="${account.LastName}" />"
                                                           placeholder="LastName" />
        </div>
        <div>
            <label for="Country">Course</label> <input type="text" name="Country"
                                                      value="<c:out value="${account.Country}" />" placeholder="Country" />
        </div>
                <div>
            <input type="submit" value="Submit" />
        </div>
    </fieldset>
</form>
</body>
</html>