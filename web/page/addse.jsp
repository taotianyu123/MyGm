<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
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
        <form action="doadmin?action=addse" method="post">

            <label>商家名称:</label>
            <input type="text" name="sellerName" /><br>
            <label>管理员名称:</label>
            <input type="text" name="sellerUser"  />&nbsp;&nbsp;&nbsp;
            <label>商家密码:</label>
            <input type="text" name="sellerPassword" /><br>
            <label>管理员性别:</label>
            <input type="radio" name="sellerSex" value="男" checked/>男
            <input type="radio" name="sellerSex" value="女" />女
            <br/>
            <label>生日:</label>
            <input type="text" name="sellerBirthday"/><br/>
            <label>身份证:</label>
            <input type="text" name="sellerIdCard"  v/>&nbsp;&nbsp;&nbsp;
            <label>商家邮箱:</label>
            <input type="text" name="sellerEmail" /><br/>
            <label>商家电话:</label>
            <input type="text" name="sellerTel"  />&nbsp;&nbsp;&nbsp;
            <label>商家地址:</label>
            <input type="text" name="sellerAddress"  /><br/>

            <input class="layui-btn layui-btn-lg" type="submit"  value="保存资料"/>
        </form>
    </div>

</body>
</html>
