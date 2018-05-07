<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cc" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">Back to main menu</a>
<br/>

<h1>Add User</h1>

    <form:form action="${path}" modelAttribute="user">
        <table>
                <tr>
                    <td>
                        <form:label path="login">
                            <spring:message text="Login"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="login"/>
                    </td>
                </tr>
            <tr>
                <td>
                    <form:label path="password">
                        <spring:message text="Password"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="password"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                        <input type="submit" value="<spring:message text="sign up"/>"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
