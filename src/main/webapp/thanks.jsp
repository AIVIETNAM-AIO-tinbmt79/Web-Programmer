
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanks for joining</title>
</head>
<body>

<h1>Thanks for joining our email list</h1>

<p>Here is the information that you entered:</p>

<label>Email:</label>
<span>${requestScope.user.email}</span>
<br>

<label>First Name:</label>
<span>${requestScope.user.firstName}</span>
<br>

<label>Last Name:</label>
<span>${requestScope.user.lastName}</span>
<br>

<label>Date of Birth:</label>
<span>${requestScope.user.dob}</span>
<br>

<h2>Additional information</h2>

<label>Current date:</label>
<span>
    <fmt:formatDate value="${requestScope.currentDate}"
                    pattern="dd/MM/yyyy"/>
</span>
<br>

<label>Customer service email:</label>
<span>${initParam.custServEmail}</span>
<br>

<h2>First user in the session</h2>

<c:if test="${not empty sessionScope.users}">
    <p>
            ${sessionScope.users[0].firstName}
            ${sessionScope.users[0].lastName}
        - ${sessionScope.users[0].email}
        - ${sessionScope.users[0].dob}
    </p>
</c:if>

<h2>Second user in the session</h2>

<c:if test="${not empty sessionScope.users
              and fn:length(sessionScope.users) >= 2}">
    <p>
            ${sessionScope.users[1].firstName}
            ${sessionScope.users[1].lastName}
        - ${sessionScope.users[1].email}
        - ${sessionScope.users[1].dob}
    </p>
</c:if>

<form action="emailList" method="get">
    <input type="hidden" name="action" value="join">
    <button type="submit">Return</button>
</form>

</body>
</html>
