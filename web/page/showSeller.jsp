<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <style>
        input{
            border-radius: 5px;
            height: 30px;
            font-size: 15px;
            padding-left: 5px;
        }
    </style>
</head>
<body>

    <c:choose>
        <c:when test="${not empty sessionScope.Page.pageDate}">
            <table id="stu" width="90%" class="layui-table" lay-size="sm">
                <tr>
                    <th>编号</th>
                    <th>商家名称</th>
                    <th>商家管理员</th>
                    <th>商家密码</th>
                    <th>商家性别</th>
                    <th>商家生日</th>
                    <th>商家身份证</th>
                    <th>商家邮箱</th>
                    <th>商家电话</th>
                    <th>商家地址</th>
                    <th colspan="2">操作</th>
                </tr>
                <c:forEach items="${sessionScope.Page.pageDate}" var="se">
                    <tr>
                        <td>${se.id}</td>
                        <td>${se.sellerName}</td>
                        <td>${se.sellerUser}</td>
                        <td>${se.sellerPassword}</td>
                        <td>${se.sellerSex}</td>
                        <td>${se.sellerBirthday}</td>
                        <td>${se.sellerIdCard}</td>
                        <td>${se.sellerEmail}</td>
                        <td>${se.sellerTel}</td>
                        <td>${se.sellerAddress}</td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=delByseId&seid=${se.id}">删除</a></td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=queryByseId&seid=${se.id}">修改</a></td>

                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="9">
                        <%@include file="PageUtil.jsp"%>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <h3>暂无学生信息</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
