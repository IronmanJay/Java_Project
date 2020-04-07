<%--
  Created by IntelliJ IDEA.
  User: IronmanJay
  Date: 2020/2/15
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Success Page.</h1>
    username:${requestScope.username}
    <br>
    password:${requestScope.password}
    <br>
    loginMsg:${requestScope.loginMsg}
</body>
</html>
