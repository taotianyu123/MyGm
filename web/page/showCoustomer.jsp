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
<form action="doadmin?action=unionQuery&pageNumber=1" method="post">
    <label>学号:</label>
    <input type="text" name="Id" placeholder="请输入编号" />
    <label>姓名:</label>
    <input type="text" name="userName" placeholder="请输入姓名"  />
    <label>性别:</label>
    <select name="userSex">
        <option value="">请选择</option>
        <option value="男">男</option>
        <option value="女">女</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
</form>
    <c:choose>
        <c:when test="${not empty sessionScope.Page.pageDate}">
            <table id="stu" width="90%" class="layui-table" lay-size="sm">
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>登录名</th>
                    <th>邮箱</th>
                    <th>性别</th>
                    <th>爱好</th>
                    <th>身份证</th>
                    <th>生日</th>
                    <th>密码</th>
                    <th>头像</th>
                    <th colspan="2">操作</th>
                </tr>
                <c:forEach items="${sessionScope.Page.pageDate}" var="cus">
                    <tr>
                        <td>${cus.id}</td>
                        <td>${cus.cusName}</td>
                        <td>${cus.cusLoginName}</td>
                        <td>${cus.cusEmail}</td>
                        <td>${cus.cusSex}</td>
                        <td>${cus.cusHobby}</td>
                        <td>${cus.cusCode}</td>
                        <td>${cus.cusBirthday}</td>
                        <td>${cus.cusPassword}</td>
                        <td><img class="layui-nav-img" width="36px" src="customerImage/${cus.cusPhoto}"></td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=delById&cusid=${cus.id}">删除</a></td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=queryBycusId&cusid=${cus.id}">修改</a></td>

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
