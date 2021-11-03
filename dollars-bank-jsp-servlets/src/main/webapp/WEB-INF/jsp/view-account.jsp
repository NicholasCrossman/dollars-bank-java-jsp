<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <li><a class="active" href="/account">Home</a></li>
                <li><a href="/deposit">Make a Deposit</a></li>
                <li><a href="/withdrawl">Make a Withdrawl</a></li>
                <li><a href="/transfer">Transfer Funds</a></li>
                <li><a href="/change-password">Change Your Password</a></li>
                <li><a href="/logout">Logout</a></li>
        
            </ul>
            <br/>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Message</th>
                        <th>Amount</th>
                        <th>Balance Before</th>
                        <th>New Balance</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${transactions}" var="transaction">
                        <tr>
                            <td>${transaction.date}</td>
                            <td>${transaction.message}</td>
                            <td>${transaction.amount}</td>
                            <td>${transaction.prevBalance}</td>
                            <td>${transaction.newBalance}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
    </body>
</html>