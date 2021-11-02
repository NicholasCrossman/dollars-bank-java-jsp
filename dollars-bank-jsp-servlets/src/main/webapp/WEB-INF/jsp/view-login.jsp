<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="loginDiv">
            <c:if test="${loginFailure}">
                <div>Username or password is incorrect.</div>
            </c:if>
        
            <form:form action="login" method="post" modelAttribute="login">
                <form:label path="email">Email: </form:label> <form:input type="email" path="email"/>
                <form:label path="password">Password: </form:label> <form:input type="password" path="password"/>
                <input type="submit" class="button" value="submit"/>
            </form:form>

            <a href="/register">Register</a>
        </div>       
    </body>
</html>