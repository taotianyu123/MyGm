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
<form action="doadmin?action=uniongoodsQuery&pageNumber=1" method="post">
    <label>编号:</label>
    <input type="text" name="Id" placeholder="请输入编号" />
    <label>商品名称:</label>
    <input type="text" name="goodsName" placeholder="请输入姓名"  />
    <label>商家名称:</label>
    <select name="sellerName">
        <option value="">请选择</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
</form>
    <c:choose>
        <c:when test="${not empty sessionScope.Page.pageDate}">
            <table id="stu" width="90%" class="layui-table" lay-size="sm">
                <tr>
                    <th>编号</th>
                    <th>商品名称</th>
                    <th>所属小分类</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>运费</th>
                    <th>类型</th>
                    <th>商家</th>
                    <th>商品折扣</th>
                    <th>图片</th>
                    <th colspan="2">操作</th>
                </tr>
                <c:forEach items="${sessionScope.Page.pageDate}" var="god">
                    <tr>
                        <td>${god.id}</td>
                        <td>${god.goodsName}</td>
                        <td>${god.smallclass.smallName}</td>
                        <td>${god.goodsMoney}</td>
                        <td>${god.goodsNumber}</td>
                        <td>${god.goodsCarriage}</td>
                        <td>${god.goodsType}</td>
                        <td>${god.seller.sellerName}</td>
                        <td>${god.discount.discRate}</td>
                        <td><img class="layui-nav-img" width="36px" src="GoodsImage/${god.goodsImage}"></td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=delBygoodsId&goodsid=${god.id}">删除</a></td>
                        <td><a class="layui-btn layui-btn-sm" href="doadmin?action=queryBygoodsId&goodsid=${god.id}">修改</a></td>
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
            <h3>暂无商品信息</h3>
        </c:otherwise>
    </c:choose>
</body>
<script>
    $(function () {
        var url = "doadmin";
        var parameterData = {action:"showsellname"}
        $.getJSON(url,parameterData,function (selist) {
            //jq中增强for循环
            $.each(selist,function(index,se){
                $("select[name='sellerName']").append("<option value='"+se.sellerName+"'>"+se.sellerName+"</option>")
            })
        })
    });
</script>
</html>
