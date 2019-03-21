<%--
  Created by IntelliJ IDEA.
  User: 小凌
  Date: 2019/3/18
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h3>用户列表</h3>
    <form action="<%=request.getContextPath()%>/tbuser/listPage" method="post">
        <div>用户姓名:<input type="text" name="name" value="${map.name}">年龄：<input type="text" name="age"  value="${map.age}">
            <input type="submit" value="查询"></div>
    </form>
    <table>
        <tr><td>用户姓名</td><td>用户年龄</td><td>用户邮箱</td><td>操作</td></tr>
        <c:forEach items="${userPage}" var="user">
            <tr><td>${user.name}</td><td>${user.age}</td><td>${user.email}</td><td>更新&nbsp删除</td></tr>
        </c:forEach>
        <tr><td colspan="4" align="center">${pageString} </td></tr>
    </table>
</center>
</body>
</html>
