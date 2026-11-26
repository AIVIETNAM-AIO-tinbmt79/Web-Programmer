
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>

<h1>Download page</h1>

<p>
    Thanks for registering,
    <c:out value="${sessionScope.user.firstName}"/>.
</p>

<p>
    Product code:
    <c:out value="${sessionScope.productCode}"/>
</p>

<p>Your download would start here.</p>

<p>
    <a href="download?action=viewAlbums">
        Return to the list of albums
    </a>
</p>

<p>
    <a href="cart?action=view">View Cart</a>
</p>

</body>
</html>
