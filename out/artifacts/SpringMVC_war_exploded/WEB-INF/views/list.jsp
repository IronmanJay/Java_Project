<%--
  Created by IntelliJ IDEA.
  User: IronmanJay
  Date: 2020/2/18
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入JSTL的标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%--  引入JQuery  --%>
    <script type="text/javascript" src="scripts/jquery-1.7.2.min.js"></script>
    <script>
        $(function () {
            // 给删除的<a>动态绑定绑定事件
            $(".del").click(function () {
                // 确认是否要删除
                var flag = window.confirm("是否要删除？");
                if(!flag){
                    return false;
                }
                // this：当前点击的dom对象
                // 获取点击的超链接的href的值
                var href = $(this).attr("href");
                // 将href的值设置到表单的action上，并提交
                $("form").attr("action",href).submit();
                // 取消<a>的默认行为
                return false;
            });
        });
    </script>
</head>
<body>
    <form action="" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>
    <h1 align="center">员工信息列表</h1>
    <table border="1px" align="center" width="70%" cellspacing="0px">
        <tr>
            <th>ID</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Gender</th>
            <th>DeptName</th>
            <th>Operation</th>
        </tr>
        <%--    通过迭代模型数据，生成表格    --%>
        <c:forEach items="${emps}" var="emp">
            <tr align="center">
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender==0?"女":"男"}</td>
                <td>${emp.department.departmentName}</td>
                <td>
                    <a href="emp/${emp.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;
                    <a class="del" href="emp/${emp.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%--  去往添加页面，不能直接进行页面的简单跳转，因为添加页面
          中需要用到部门数据，因此需要到Handler中想查询到部门数据，在添加进来--%>
    <h2 align="center"><a href="emp">Add New Emp</a></h2>
</body>
</html>
