<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Your Account</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h2>Name: ${account.name}</h2>
        <h2>Email: ${account.email}</h2>
        <h2>Account ID: ${account.accountId}</h2>
        <h2>Name: ${account.name}</h2>
        <br/>
        <h2>What would you like to do?</h2>
        <ul>
            <li><a href="/account">Home</a></li>
            <li><a href="/deposit">Make a Deposit</a></li>
            <li><a class="active" href="/withdrawl">Make a Withdrawl</a></li>
            <li><a href="/transfer">Transfer Funds</a></li>
            <li><a href="/change-password">Change Your Password</a></li>
    
        </ul>
        <br/>

        <c:if test="${withdrawlFailure}">
            <div>Withdrawl cannot be zero.</div>
        </c:if>
    
        <form:form action="withdrawl" method="post" modelAttribute="amount">
            <fieldset>
                <form:label path="value">Amount: </form:label> 
                <form:input type="text" path="value"/> <br/>
            </fieldset>
            <input type="submit" class="button" value="submit"/>
        </form:form>
    </body>
</html>