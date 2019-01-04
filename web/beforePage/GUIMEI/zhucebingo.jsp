<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\1\3 0003
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>

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
            if(time!=0){
                document.getElementsByTagName("B")[0].innerHTML=time;
                setTimeout(yanshi,1000);
                time--;
            }else{
                location="login.html";
            }
        }
    </script>
</head>
<body onload="yanshi()">
<div id="bingo_d">
    <center>
        <img src="../images/img/images/logo.jpg">
        <p>祝贺!</p>
        <p>您已经注册成功!</p>
        <p>欢迎来到 拍卖天堂</p>
        <label>登录成功，系统将会在<label><B>3</B></label>s后跳转</label>
        <p>如过没有跳转请单击<a style="font-family: '黑体'" href="login.html">这里</a></p>
    </center>
</div>
</body>
</html>