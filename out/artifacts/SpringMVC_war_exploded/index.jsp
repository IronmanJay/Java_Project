<%--
  Created by IntelliJ IDEA.
  User: IronmanJay
  Date: 2020/2/15
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
      <a href="testInterceptor">Test Interceptor</a>
      <br>
      <form action="upload" method="post" enctype="multipart/form-data">
            上传文件：<input type="file" name="uploadFile">
            <br>
            文件描述：<input type="text" name="desc">
            <br>
            <input type="submit" value="上传">
      </form>
      <br>
      <a href="download">点我下载</a>
      <br>
      <a href="testJson">Test Json</a>
      <br>
      <a href="emps">List ALL Emps</a>
      <br>
      <a href="testRedirectView">Test RedirectView</a>
      <br>
      <a href="testViewContorller">Test ViewContorller</a>
      <br>
      <a href="testView">Test View</a>
      <br>
      <a href="testMedel">Test Medel</a>
      <br>
      <a href="testMap">Test Map</a>
      <br>
      <a href="testModelAndView">Test ModelAndView</a>
      <br>
      <a href="testServletAPI">Test ServletAPI</a>
      <br>
    <form action="testPOJO" method="post">
        用户名称:<input type="text" name="username">
        <br>
        用户密码:<input type="password" name="password">
        <br>
        用户邮箱:<input type="text" name="email">
        <br>
        用户性别:男<input type="radio" name="gender" value="1">
                女<input type="radio" name="gender" value="0">
        <br>
        <%--   支持级联的方式     --%>
        用户省份:<input type="text" name="address.province">
        <br>
        用户城市:<input type="text" name="address.city">
        <br>
        <input type="submit" value="注册">
    </form>
    <br />
    <a href="testCookieValue">Test CookieValue</a>
    <br />
    <a href="testRequestHeader">Test RequestHeader</a>
    <br />
    <a href="testRequestParam?username=Tom&age=22">Test RequestParam</a>
    <br />
    <%-- 修改一个订单 --%>
    <form action="order" method="post">
      <input type="hidden" name="_method" value="POST">
      <input type="submit" value="REST PUT">
    </form>
    <br />
    <%-- 添加一个新的订单 --%>
    <form action="order" method="post">
      <input type="submit" value="REST POST">
    </form>
    <br />
    <%-- 删除id为1001的订单 --%>
    <form action="order/1001" method="post">
      <!-- 隐藏域· -->
      <input type="hidden" name="_method" value="GET"/>
      <input type="submit" value="REST DELETE"/>
    </form>
    <br />
    <%-- 查询id为1001的订单 --%>
    <a href="order/1001">Test GET</a>
    <br />
    <a href="testPathVariable/admin/1001">Test PathVariable</a>
    <br />
    <a href="testRequestMappingParamsAndHanders?username=Tom&age=22">Test RequestMappingParamsHanders</a>
    <br />
    <form action="testRequestMappingMethod" method="post">
      <input type="submit" value="POST">
    </form>
    <br />
    <a href="testRequestMappingMethod">Test RequestMappingMethod</a>
    <br />
    <a href="testRequestMapping">Test RequestMapping</a>
    <br />
    <a href="hello">Hello SpringMvc</a>
  </body>
</html>
