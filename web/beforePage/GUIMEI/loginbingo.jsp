<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\1\3 0003
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="../js/jquery-2.1.0.js"></script>
    <link href="../css/guimei.css" rel="stylesheet" type="text/css">
    <style>
        #bingo_d label{
            text-align: center;
        }
    </style>
    <script>
        //三秒跳转
        var time=3;
        function yanshi(){
            document.getElementsByTagName("B")[0].innerHTML=time;
            // 给 第一个 <B> 标签 的 字段 赋值
            if(time!=0){
                setTimeout(yanshi,1000);
                //一秒延时 调用方法
                time--;
                //循环次数
            }else{
                open("frame.html",target="_parent");
                //本窗口跳转链接
            }
        }

        $(function(){
            $("[name='here']").click(function(){
                open("frame.html",target="_parent");
            });
        });
    </script>
</head>
<body onload="yanshi()">
<div id="bingo_d">
    <center>
        <img src="../images/img/images/logo.jpg">
        <p>祝贺!</p>
        <p>您已经成功登录!</p>
        <p>欢迎来到 拍卖天堂</p>
        <label>登录成功，系统将会在<label><B>3</B></label>s后跳转</label>
        <p>如过没有跳转请单击<b style="font-family: '黑体';color:red" name="here">这里</b></p>
    </center>
</div>
</body>
</html>
