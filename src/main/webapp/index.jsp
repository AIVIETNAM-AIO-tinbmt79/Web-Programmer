
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Join our email list</title>
</head>
<body>

<h1>Join our email list</h1>

<form action="emailList" method="post">
    <input type="hidden" name="action" value="add">

    <label>Email:</label>
    <input type="email" name="email"
           value="${sessionScope.user.email}" required>
    <br>

    <label>First Name:</label>
    <input type="text" name="firstName"
           value="${sessionScope.user.firstName}" required>
    <br>

    <label>Last Name:</label>
    <input type="text" name="lastName"
           value="${sessionScope.user.lastName}" required>
    <br>

    <label>Date of Birth:</label>
    <input type="date" name="dob"
           value="${sessionScope.user.dob}">
    <br><br>

    <button type="submit">Join</button>
</form>

</body>
</html>
