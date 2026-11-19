<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>

<h1>Checkout</h1>

<h2>Cảm ơn bạn đã lựa chọn sản phẩm</h2>

<p>Đơn hàng của bạn đã được ghi nhận thành công.</p>

<%-- Xóa giỏ hàng trong Session sau khi thanh toán thành công --%>
<c:remove var="cart" scope="session"/>

<br>
<form action="cart" method="post">
    <input type="hidden" name="action" value="shop">
    <input type="submit" value="Continue Shopping">
</form>

</body>
</html>