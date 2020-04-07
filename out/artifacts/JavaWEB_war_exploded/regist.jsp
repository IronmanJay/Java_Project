<%--
  Created by IntelliJ IDEA.
  User: IronmanJay
  Date: 2020/2/7
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        span {
            color: red;
        }
    </style>
    <%--  引入jquery  --%>
    <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        // 获取 xhr 对象
        function getXhr() {
            var xhr = new XMLHttpRequest();
            //alert(xhr);
            return xhr;
        }

        function checkUsername() {
            //获取用户输入的用户名。
            var username = document.getElementById("username").value;
            //发送异步请求进行校验
            var xhr = getXhr();

            //设置请求信息
            xhr.open("get", "checkUsername?username=" + username, true);
            //发送请求
            xhr.send();
            //监听readyState的状态
            xhr.onreadystatechange = function () {  //匿名函数
                if (xhr.readyState == 4) {  // 响应处理完成
                    if (xhr.status == 200) { //处理正确
                        //获取服务端响应回来的数据.
                        var msg = xhr.responseText;
                        //将信息显示到用户名输入框后面
                        document.getElementById("regist_span").innerHTML = msg;
                    }
                }
            }
        }

        $(function () {
            var usernameFlag = false;
            var passwordFlag = false;

            $("#username").blur(function () {
                var username = $("#username").val();
                //发送异步请求
                $.ajax({
                    url: "checkUsername?username=" + username,
                    type: "get",
                    success: function (data) {  // 会将服务器返回的数据保存到data中
                        if (data == 0) {
                            $("#regist_span").html("用户名可以使用");
                            usernameFlag = true;
                        }else {
                            $("#regist_span").html("用户名不可以使用");
                        }
                    }
                });
            });
            $("#repassword").blur(function () {
                var password = $("#password").val();
                var repassword = $("#repassword").val();
                if (password != null && password != "" && password == repassword) {
                    $("#pass_span").html("密码一致");
                    passwordFlag = true;
                } else {
                    $("#pass_span").html("密码不一致");
                }
            });
            $("form").submit(function () {
                return usernameFlag && passwordFlag;
            });
        });

    </script>
</head>
<body>
<h1>欢迎注册</h1>
<form action="regist" method="post">
    用户名称:<input type="text" id="username" name="username"/><span id="regist_span">${regist_msg }</span>
    <br/>
    用户密码:<input type="password" id="password" name="password">
    <br/>
    确认密码:<input type="password" id="repassword" name="repassword"><span id="pass_span"></span>
    <br/>
    <input type="submit" value="注册">
</form>
</body>
</html>
