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
        <form action="doadmin?action=updateBysmId" method="post">
            <label>编号:</label>
            <input type="text" name="Id" value="${sessionScope.ExtSmallclass.id}" readonly/><br>
            <label>小分类名:</label>
            <input type="text" name="smName" value="${sessionScope.ExtSmallclass.smallName}" /><br>
            <label>大分类:</label>
            <select name="bigname">
                <option value="${sessionScope.ExtSmallclass.smallBigId}">${sessionScope.ExtSmallclass.bigclass.bigName}</option>
            </select>
            <label>描述:</label>
            <input type="text" name="smText"  value="${sessionScope.ExtSmallclass.smallText}" /><br>
            <input class="layui-btn layui-btn-lg" type="submit"  value="保存资料"/>
        </form>
    </div>

</body>
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
</html>
