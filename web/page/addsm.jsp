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
        <form action="doadmin?action=addsm" method="post">
            <label>小分类名称:</label>
            <input type="text" name="smname" required  lay-verify="required"  autocomplete="off" placeholder="请输入小分类名称"  class="layui-input"><br/>
            <label>大分类名称:</label>
            <select name="bigname">
                <option value="">请选择</option>
            </select>
            <br/>
            <br/>
            <label>小分类描述:</label>
            <input type="text" name="smtext" required  lay-verify="required"  autocomplete="off" placeholder="请输入小分类描述"  class="layui-input"><br/>
            <input class="layui-btn layui-btn-lg" type="submit"  value="保存资料"/>
        </form>
    </div>
    <script>
        $(function () {
            var url = "doadmin";
            var parameterData = {action:"showbigclass"}
            $.getJSON(url,parameterData,function (biglist) {
                //jq中增强for循环
                $.each(biglist,function(index,big){
                    $("select[name='bigname']").append("<option value='"+big.id+"'>"+big.bigName+"</option>")
                })
            })
        });
    </script>

</body>
</html>
