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
        <form action="doadmin?action=addgods" method="post">
            <label id="ts"></label>
            <label>商品名称:</label>
            <input id="godsName" type="text" name="godsName"  /><br/>
            <label>小分类名称:</label>
            <select name="smclassName">
            </select>
            &nbsp;&nbsp;&nbsp;
            <label>单价:</label>
            <input type="text" name="goodsMoney"   /><br>
            <label>数量:</label>
            <input type="text" name="goodsNumber"   />&nbsp;&nbsp;&nbsp;
            <label>运费:</label>
            <input type="text" name="goodsCarriage"  /><br>
            <label>类型:</label>
            <input type="text" name="goodsType"   /><br/>
            <label>商品折扣:</label>
            <select name="discRate">
            </select><br/>
            <input hidden type="text" name="sellerName" value="${seid}"/>

            <input type="file" name="userImage"/>
            <br/>

            <input class="layui-btn layui-btn-lg" type="submit"  value="保存资料"/>
        </form>
    </div>
    <script>

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

        $("#godsName").blur(function () {
            var godname=$(this).val().trim();
            var sellerid=$("#sellerName").val().trim();
            var url = "doadmin";
            var parameterData = "action=pdname&godname="+godname+"&sellerid="+sellerid;
            $getJSON(url,parameterData,function (i) {
                if (i>0){
                    $("#ts").html("此商品已有!").cos("color","red");
                    $("#tj").prop("disabled",true);
                }else{
                    $("#ts").html("暂无此商品!").cos("color","red");
                    $("#tj").prop("disabled",false);
                }

            },"json")
        })
        $("#sellerName").blur(function () {
            var godname=$("#godsName").val().trim();
            var sellerid=$(this).val();
            var url = "doadmin";
            var parameterData = "action=pdname&godname="+godname+"&sellerid="+sellerid;
            $getJSON(url,parameterData,function (i) {
                if (i>0){
                    $("#ts").html("此商品已有!").cos("color","red");
                    $("#tj").prop("disabled",true);
                }else{
                    $("#ts").html("暂无此商品!").cos("color","red");
                    $("#tj").prop("disabled",false);
                }

            },"json")

        })
    </script>

</body>
</html>
