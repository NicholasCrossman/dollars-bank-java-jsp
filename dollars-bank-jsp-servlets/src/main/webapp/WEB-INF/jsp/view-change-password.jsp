<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Change Password</title>
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
            <ul>
                <li><a href="/account">Home</a></li>
                <li><a class="active" href="/deposit">Make a Deposit</a></li>
                <li><a href="/withdrawl">Make a Withdrawl</a></li>
                <li><a href="/transfer">Transfer Funds</a></li>
                <li><a href="/change-password">Change Your Password</a></li>
                <li><a href="/logout">Logout</a></li>
        
            </ul>
            <br/>
            <h1>Change Your Password</h1>

            <c:if test="${passwordIncorrect}">
                <div>The old password was incorrect</div>
            </c:if>
            <c:if test="${doesNotMatch}">
                <div>Please enter the new password twice.</div>
            </c:if>
        
            <form:form action="change-password" method="post" modelAttribute="changePassword">
                <fieldset>
                    <form:label path="oldPassword">Old Password: </form:label> 
                    <form:input type="password" path="oldPassword"/> <br/>

                    <form:label path="newPassword">New Password: </form:label> 
                    <form:input type="password" path="newPassword"/> <br/>

                    <form:label path="newPasswordMatch">Confirm New Password: </form:label> 
                    <form:input type="password" path="newPasswordMatch"/> <br/>
                </fieldset>
                <input type="submit" class="button" value="submit"/>
            </form:form>
        </div>        
    </body>
</html>