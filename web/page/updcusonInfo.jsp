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
        <form action="doadmin?action=updateBycusId" method="post">
            <label>编号:</label>
            <input type="text" name="Id" value="${sessionScope.Customer.id}" readonly/>&nbsp;&nbsp;&nbsp;
            <label>姓名:</label>
            <input type="text" name="cusName" value="${sessionScope.Customer.cusName}" readonly/><br>
            <label>登录名:</label>
            <input type="text" name="cusloginname"  value="${sessionScope.Customer.cusLoginName}" />&nbsp;&nbsp;&nbsp;
            <label>邮箱:</label>
            <input type="text" name="cusemail"  value="${sessionScope.Customer.cusEmail}" /><br>
            <label>性别:</label>
            <input type="radio" name="cusSex" value="男" ${sessionScope.Customer.cusSex=='男'?'checked':''} />男
            <input type="radio" name="cusSex" value="女" ${sessionScope.Customer.cusSex=='女'?'checked':''}/>女
            <br/>
            <label>爱好:</label>
            <input type="text" name="cushobby"  value="${sessionScope.Customer.cusHobby}" />&nbsp;&nbsp;&nbsp;
            <label>身份证:</label>
            <input type="text" name="cuscode"  value="${sessionScope.Customer.cusCode}" /><br>
            <label>生日:</label>
            <input type="text" name="cusbirthday"  value="${sessionScope.Customer.cusBirthday}" /><br/>
            <input class="layui-btn layui-btn-lg" type="submit"  value="保存资料"/>
        </form>
    </div>

</body>
</html>
