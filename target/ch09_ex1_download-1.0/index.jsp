
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>

<h1>Downloads</h1>

<c:if test="${not empty cookie.firstNameCookie.value}">
    <p>
        Welcome back,
        <c:out value="${cookie.firstNameCookie.value}"/>!
    </p>
</c:if>

<c:if test="${not empty cartMessage}">
    <p><c:out value="${cartMessage}"/></p>
</c:if>

<h2>List of albums</h2>

<table>
    <tr>
        <th>Album</th>
        <th>Price</th>
        <th>Download</th>
        <th>Cart</th>
    </tr>

    <tr>
        <td>86 (the band) - True Life Songs and Pictures</td>
        <td>$14.95</td>
        <td>
            <a href="download?action=checkUser&amp;productCode=8601">
                Download
            </a>
        </td>
        <td>
            <form action="cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productCode" value="8601">
                <input type="submit" value="Add To Cart">
            </form>
        </td>
    </tr>

    <tr>
        <td>Paddlefoot - The first CD</td>
        <td>$12.95</td>
        <td>
            <a href="download?action=checkUser&amp;productCode=pf01">
                Download
            </a>
        </td>
        <td>
            <form action="cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productCode" value="pf01">
                <input type="submit" value="Add To Cart">
            </form>
        </td>
    </tr>

    <tr>
        <td>Paddlefoot - The second CD</td>
        <td>$14.95</td>
        <td>
            <a href="download?action=checkUser&amp;productCode=pf02">
                Download
            </a>
        </td>
        <td>
            <form action="cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productCode" value="pf02">
                <input type="submit" value="Add To Cart">
            </form>
        </td>
    </tr>

    <tr>
        <td>Joe Rut - Genuine Wood Grained Finish</td>
        <td>$14.95</td>
        <td>
            <a href="download?action=checkUser&amp;productCode=jr01">
                Download
            </a>
        </td>
        <td>
            <form action="cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productCode" value="jr01">
                <input type="submit" value="Add To Cart">
            </form>
        </td>
    </tr>
</table>

<p>
    <a href="cart?action=view">View Cart</a>
</p>

<p>
    <a href="download?action=viewCookies">View all cookies</a> |
    <a href="download?action=deleteCookies">Delete cookies</a>
</p>

</body>
</html>
