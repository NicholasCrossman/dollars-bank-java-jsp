<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Your Account</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h4>Name: ${account.name}</h4>
        <h4>Email: ${account.email}</h4>
        <h4>Account ID: ${account.accountId}</h4>
        <h4>Name: ${account.name}</h4>
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
                <c:forEach items="${account.transactions}" var="transaction">
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
    </body>
</html>