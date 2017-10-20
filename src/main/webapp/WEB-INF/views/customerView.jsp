<%--
  Created by IntelliJ IDEA.
  User: novabase
  Date: 20-10-2017
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>JavaBank</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div>
    <h2>Customer List</h2>
    <table>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
        </tr>
        <tr th:each="customer : ${customers}">
            <td th:text="${customer.id}"></td>
            <td th:text="${customer.firstName}"></td>
            <td th:text="${customer.lastName}"></td>
            <td th:text="${customer.email}"></td>
            <td th:text="${customer.phone}"></td>
        </tr>
    </table>
</div>
</body>
</html>
