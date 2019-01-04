<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
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
                    <th>通告标题</th>
                    <th>内容</th>
                    <th>日期</th>
                    <th colspan="2">操作</th>
                </tr>
                <c:forEach items="${sessionScope.Page.pageDate}" var="ann">
                    <tr>
                        <td>${ann.id}</td>
                        <td>${ann.anTitle}</td>
                        <td>${ann.anText}</td>
                        <td>${ann.anDate}</td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=delByannId&annId=${ann.id}">删除</a></td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=queryByannId&annId=${ann.id}">修改</a></td>


                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6">
                        <%@include file="PageUtil.jsp"%>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <h3>暂无成绩信息</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
