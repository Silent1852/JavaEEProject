<%@ page language="java" contentType="text/html; charset=Windows-1251" pageEncoding="Windows-1251" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
<form method="post" action="logout">
    <%
        String login = (String)request.getAttribute("dataForView");
    %>
    <%="Hello,  "+login+"!"%>
    <a href="logout">Logout</a>
</form>
<h2>Products List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<table>
    <tr><th>Name</th><th>Price</th><th></th></tr>
    <c:forEach var="product" items="${products}">
        <tr><td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <a href='<c:url value="/edit?id=${product.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${product.id}">
                    <input type="submit" value="Delete">
                </form>
            </td></tr>
    </c:forEach>
</table>
</body>
</html>