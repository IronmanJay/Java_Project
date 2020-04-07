<%--
  Created by IntelliJ IDEA.
  User: IronmanJay
  Date: 2020/2/6
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <style>
        body{
            background-color:pink;
        }
        span{
            color: red;
        }
    </style>
    <script type="text/javascript">
        function clearLoginMsg() {
            var spanEle = document.getElementById("login_msg");
            spanEle.innerHTML = "";
        }
    </script>
</head>
<body>
<h1>欢迎登录</h1>
<form action="login" method="post">
    用户名称:<input type="text" name="username" onfocus="clearLoginMsg();"/>
        <span id="login_msg">${requestScope.login_msg}</span>
    <br/>
    用户密码 :<input type="password" name="password"/>
    <br/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
