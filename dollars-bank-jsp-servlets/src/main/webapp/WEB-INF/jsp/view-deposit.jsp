<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Your Account</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="content">
            <h2>Your Account</h2> <br>
        
            Name: ${account.name} <br>
            Email: ${account.email} <br>
            Account ID: ${account.accountId} <br>
            Balance: ${account.balance} <br>
            <br/>
            <h2>What would you like to do?</h2>
            <ul>
                <li><a href="/account">Home</a></li>
                <li><a class="active" href="/deposit">Make a Deposit</a></li>
                <li><a href="/withdrawl">Make a Withdrawl</a></li>
                <li><a href="/transfer">Transfer Funds</a></li>
                <li><a href="/change-password">Change Your Password</a></li>
                <li><a href="/logout">Logout</a></li>
        
            </ul>
            <br/>

            <c:if test="${depositFailure}">
                <div>Deposit cannot be zero.</div>
            </c:if>
        
            <form:form action="deposit" method="post" modelAttribute="amount">
                <fieldset>
                    <form:label path="value">Amount: </form:label> 
                    <form:input type="text" path="value"/> <br/>
                </fieldset>
                <input type="submit" class="button" value="submit"/>
            </form:form>
        </div>        
    </body>
</html>