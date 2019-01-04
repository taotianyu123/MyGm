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
<form action="doadmin?action=unionsmQuery&pageNumber=1" method="post">
    <label>编号:</label>
    <input type="text" name="Id" placeholder="请输入编号" />
    <label>小分类名称:</label>
    <input type="text" name="smName" placeholder="请输入姓名"  />
    <label>大分类:</label>
    <select name="bigname">
        <option value="">请选择</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
</form>
    <c:choose>
        <c:when test="${not empty sessionScope.Page.pageDate}">
            <table id="stu" width="90%" class="layui-table" lay-size="sm">
                <tr>
                    <th>编号</th>
                    <th>小分类名称</th>
                    <th>大分类名称</th>
                    <th>描述</th>
                    <th colspan="2">操作</th>
                </tr>
                <c:forEach items="${sessionScope.Page.pageDate}" var="sm">
                    <tr>
                        <td>${sm.id}</td>
                        <td>${sm.smallName}</td>
                        <td>${sm.bigclass.bigName}</td>
                        <td>${sm.smallText}</td>

                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=delBysmId&smId=${sm.id}">删除</a></td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=queryBysmId&smId=${sm.id}">修改</a></td>


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
<script>
    $(function () {
        var url = "doadmin";
        var parameterData = {action:"showbigclass"}
        $.getJSON(url,parameterData,function (biglist) {
            //jq中增强for循环
            $.each(biglist,function(index,big){
                $("select[name='bigname']").append("<option value='"+big.bigName+"'>"+big.bigName+"</option>")
            })
        })
    });

</script>
</html>
