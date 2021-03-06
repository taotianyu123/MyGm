<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\12\18 0018
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=request.getContextPath() %>/" />
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../static/layui/css/layui.css">
    <script src="../static/layui/layui.js"></script>
    <style>
        body{
            background: #ffffff;
        }
        .addCustomer{
            width: 45%;
            margin: 40px auto;
        }
        label{
            color: #595959;
        }
    </style>
</head>
<body>
<div class="addCustomer">
    <form class="layui-form layui-form-pane" action="doadmin?action=addann" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">公告标题</label>
            <div class="layui-input-block">
                <input type="text" name="annTitle" required  lay-verify="required"  autocomplete="off" placeholder="请输入公告标题"  class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
                <input type="text" name="anntext" required  lay-verify="required"  autocomplete="off" placeholder="请输入内容"  class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="submit" class="layui-btn" value="提交注册"/>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>


<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
</body>
</html>
