<%--
  Created by IntelliJ IDEA.
  User: IronmanJay
  Date: 2020/2/18
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入SpringMVC表单标签库--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--导入JSTL标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!-- SpringMVC的表单标签
         1、可以快速的开发表单
         2、可以更加方便的回显数据 -->
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
    <%--   判断时添加操作还是修改操作
           根据回显的employee对象的id值来判断：如果有id就是修改，如果没有id就是添加--%>
        <c:if test="${!empty employee.id}" var="flag">
            <%--     修改操作       --%>
            <input:hidden path="id"/>
            <%--     隐藏PUT       --%>
            <input type="hidden" name="_method" value="PUT">
        </c:if>
        lastName:<form:input path="lastName" />
        <br>
        Email:<form:input path="email" />
        <br>
        Gender:<form:radiobuttons path="gender" items="${genders}" />
        <br>
        deptName:<form:select path="department.id" items="${depts}" itemLabel="departmentName" itemValue="id"></form:select>
        <br>
        <c:if test="${flag}">
            <input type="submit" value="Edit">
        </c:if>
        <c:if test="${!flag}">
            <input type="submit" value="ADD">
        </c:if>
    </form:form>
</body>
</html>
