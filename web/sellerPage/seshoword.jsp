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
                    <th>商家名称</th>
                    <th>商品名称</th>
                    <th>顾客姓名</th>
                    <th>时间</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>状态</th>
                    <th colspan="1">操作</th>
                </tr>
                <c:forEach items="${sessionScope.Page.pageDate}" var="ord">
                    <tr>
                        <td>${ord.id}</td>
                        <td>${ord.seller.sellerName}</td>
                        <td>${ord.goods.goodsName}</td>
                        <td>${ord.customer.cusName}</td>
                        <td>${ord.orderseDate}</td>
                        <td>${ord.orderseAddress}</td>
                        <td>${ord.orderseMoney}</td>
                        <td>${ord.orderseStatus}</td>
                        <%--<td><a class="layui-btn layui-btn-sm" href="doadmin?action=delBygoodsId&goodsid=${god.id}">删除</a></td>--%>
                        <td><a class="layui-btn layui-btn-sm" href="dose?action=queryByordId&ordid=${ord.id}">派送</a></td>
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
        var parameterData = {action:"showsmclass"}
        $.getJSON(url,parameterData,function (smlist) {
            //jq中增强for循环
            $.each(smlist,function(index,sm){
                $("select[name='smclassName']").append("<option value='"+sm.id+"'>"+sm.smallName+"</option>")
            })
        })
    });
</script>
</html>
