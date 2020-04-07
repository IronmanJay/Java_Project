<%@ page import="java.util.List" %>
<%@ page import="Login_Beans.Employee" %>
<%--
  Created by IntelliJ IDEA.
  User: IronmanJay
  Date: 2020/2/8
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1 align="center">  欢迎  <font color="#2e8b57">${sessionScope.loginUser.username}</font>  登录,当前在线
        <font color="blue">${applicationScope.count}</font>人</h1>
    <br />
    <h2 align="center">员工信息列表</h2>
    <c:if test="${!empty emps}">
    <table border="1px" width="70%" align="center" cellspacing="0px">
        <tr>
            <th>ID</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Gender</th>
            <th>DeptName</th>
            <th>Operation</th>
        </tr>
        <%--   通过循环显示员工数据     --%>
        <c:forEach items="${emps}" var="emp">
            <tr align="center">
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender==0?"女":"男"}</td>
                <td>${emp.dept.deptName}</td>
                <td>
                    <a href="#">Edit</a>
                    &nbsp;&nbsp;
                    <a href="#">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
    <c:if test="${empty emps}">
        <h2 align="center">没有任何员工信息</h2>
    </c:if>
    <h3 align="center"><a href="#">Add New Emp</a></h3>
</body>
</html>
