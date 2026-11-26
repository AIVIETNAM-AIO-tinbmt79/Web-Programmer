
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>

<h1>Your Cart</h1>

<c:if test="${not empty cartMessage}">
    <p><c:out value="${cartMessage}"/></p>
</c:if>

<table>
    <tr>
        <th>Quantity</th>
        <th>Description</th>
        <th>Price</th>
        <th>Amount</th>
        <th></th>
    </tr>

    <c:choose>
        <c:when test="${empty sessionScope.cart
                        || empty sessionScope.cart.items}">
            <tr>
                <td colspan="5">Your cart is empty.</td>
            </tr>
        </c:when>

        <c:otherwise>
            <c:forEach var="item"
                       items="${sessionScope.cart.items}">
                <tr>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden"
                                   name="action"
                                   value="update">

                            <input type="hidden"
                                   name="productCode"
                                   value="${item.product.productCode}">

                            <input type="number"
                                   name="quantity"
                                   min="0"
                                   value="${item.quantity}"
                                   required>

                            <input type="submit" value="Update">
                        </form>
                    </td>

                    <td>
                        <c:out value="${item.product.description}"/>
                    </td>

                    <td>
                        <fmt:formatNumber
                                value="${item.product.price}"
                                type="currency"
                                currencySymbol="$"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>
                    </td>

                    <td>
                        <fmt:formatNumber
                                value="${item.total}"
                                type="currency"
                                currencySymbol="$"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>
                    </td>

                    <td>
                        <form action="cart" method="post">
                            <input type="hidden"
                                   name="action"
                                   value="remove">

                            <input type="hidden"
                                   name="productCode"
                                   value="${item.product.productCode}">

                            <input type="submit" value="Remove Item">
                        </form>
                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="3"><strong>Total:</strong></td>
                <td>
                    <strong>
                        <fmt:formatNumber
                                value="${sessionScope.cart.total}"
                                type="currency"
                                currencySymbol="$"
                                minFractionDigits="2"
                                maxFractionDigits="2"/>
                    </strong>
                </td>
                <td></td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>

<p>
    To change the quantity, enter the new quantity and click Update.
    Enter 0 to remove an item.
</p>

<form action="cart" method="post">
    <input type="hidden" name="action" value="continue">
    <input type="submit" value="Continue Shopping">
</form>

<c:if test="${not empty sessionScope.cart
              and not empty sessionScope.cart.items}">
    <form action="cart" method="post">
        <input type="hidden" name="action" value="checkout">
        <input type="submit" value="Checkout">
    </form>
</c:if>

</body>
</html>
