<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
    <%@include file="layUI.jsp"%>
    <style>
        .personWarp{
            height: 400px;
            width: 75%;
            overflow: hidden;
            margin: auto;
            background-color: #ffffff;
            padding-left:25%;
            font-size: 16px;
            color: #696969;
        }

        .personWarp input{
           margin-top: 20px;
        }
        input[type='text']{
            border-radius: 5px;
            height: 40px;
            font-size: 15px;
            padding-left: 10px;
        }
        input[name='stuId']{
            background-color: #999999;
        }
        input[type='submit']{
           width: 225px;
        }
    </style>


</head>
<body>
    <div class="personWarp">
        <form action="doadmin?action=updateBygodsId" method="post">
            <label>编号:</label>
            <input type="text" name="Id" value="${sessionScope.ExtGoods.id}" readonly/>&nbsp;&nbsp;&nbsp;
            <label>商品名称:</label>
            <input type="text" id="godsName" name="godsName" value="${sessionScope.ExtGoods.goodsName}" /><br/>
            <label>小分类名称:</label>
            <select name="smclassName">
                <option value="${sessionScope.ExtGoods.goodsSmalId}">${sessionScope.ExtGoods.smallclass.smallName}</option>
            </select>
            &nbsp;&nbsp;&nbsp;
            <label>单价:</label>
            <input type="text" name="goodsMoney"  value="${sessionScope.ExtGoods.goodsMoney}" /><br>
            <label>数量:</label>
            <input type="text" name="goodsNumber"  value="${sessionScope.ExtGoods.goodsNumber}" />&nbsp;&nbsp;&nbsp;
            <label>运费:</label>
            <input type="text" name="goodsCarriage"  value="${sessionScope.ExtGoods.goodsCarriage}" /><br>
            <label>类型:</label>
            <input type="text" name="goodsType"  value="${sessionScope.ExtGoods.goodsType}" /><br/>
            <label>商品折扣:</label>
            <select name="discRate">
                <option value="${sessionScope.ExtGoods.goodsSeId}">${sessionScope.ExtGoods.discount.discRate}</option>
            </select><br/>
            <label>商家名称:</label>
            <select id="sellerName" name="sellerName">
                <option value="${sessionScope.ExtGoods.goodsDiscId}">${sessionScope.ExtGoods.seller.sellerName}</option>
            </select>
            <br/>
            <input class="layui-btn layui-btn-lg" type="submit" id="tj"  value="保存资料" disabled="true"/>
        </form>
    </div>
    <script>
        $(function () {
            var url = "doadmin";
            var parameterData = {action:"showsellname"}
            $.getJSON(url,parameterData,function (selist) {

                //jq中增强for循环
                $.each(selist,function(index,se){
                    $("select[name='sellerName']").append("<option value='"+se.id+"'>"+se.sellerName+"</option>")
                })
            })
        });
        $(function () {
            var url = "doadmin";
            var parameterData = {action:"showdiscount"}
            $.getJSON(url,parameterData,function (dislist) {

                //jq中增强for循环
                $.each(dislist,function(index,dis){
                    $("select[name='discRate']").append("<option value='"+dis.id+"'>"+dis.discRate+"</option>")
                })
            })
        });

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

</body>
</html>
