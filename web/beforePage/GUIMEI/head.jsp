<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\1\3 0003
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>贵美商城</title>
    <link href="../css/guimei.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../js/jquery-2.1.0.js"></script>

    <script>
        //当前时间显示
        function nowdate() {
            document.getElementById("shijian").innerHTML = new Date().toLocaleString();
            setTimeout(nowdate, 1000);
        }

        //加入收藏
        function AddFavorite(sURL, sTitle) {
            sURL = encodeURI(sURL);
            try{
                window.external.addFavorite(sURL, sTitle);
            }catch(e) {
                try{
                    window.sidebar.addPanel(sTitle, sURL, "");
                }catch (e) {
                    alert("加入收藏失败，请使用Ctrl+D进行添加,或手动在浏览器里进行设置.");
                }
            }
        }

        //设为首页
        function SetHome(url){
            if (document.all) {
                document.body.style.behavior='url(#default#homepage)';
                document.body.setHomePage(url);
            }else{
                alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");
            }
        }


        //当前用户验证
        $(function(){
            var url = "../servlet/customServlet?action=checkActiveUser";
            $.post(url,function(json){
                if(json==null||json=="null"){
                    $("#loginName").html("游客").css("color","red");
                }else{
                    $("#loginName").html(json).css("color","red");
                }
            },"text");


            /* 退出按钮 */
            $("#out").click(function(){
                location = "OutPage.jsp";
            });

            /* 我的贵美 链接 */
            $("#loginName").click(function(){
                open("userorder.html",target="body_i");
            });
            /* 搜索框按钮 */
            $("#sea").click(function(){
                $.post("../servlet/goodsManagerServlet?action=getgoodsManagerVagueQurey&pageNum=1&pageSize=5&msg=queryValue&value="+$("#ser").val());
                setTimeout(open("goodsQuery.html",target="body_i"),300);
            });
            //大分类搜索-- 家用电器
            $("#electricAppliance").click(function(){
                $.post("../servlet/goodsManagerServlet?action=getgoodsManagerVagueQurey&pageNum=1&pageSize=5&&msg=queryClassID&b_id=1");
                open("goodsQuery.html",target="body_i");
            });
            $("#phone").click(function(){
                $.post("../servlet/goodsManagerServlet?action=getgoodsManagerVagueQurey&pageNum=1&pageSize=5&&msg=queryClassID&b_id=2");
                open("goodsQuery.html",target="body_i");
            });
            $("#baihuo").click(function(){
                $.post("../servlet/goodsManagerServlet?action=getgoodsManagerVagueQurey&pageNum=1&pageSize=5&&msg=queryClassID&b_id=3");
                open("goodsQuery.html",target="body_i");
            });
            $("#book").click(function(){
                $.post("../servlet/goodsManagerServlet?action=getgoodsManagerVagueQurey&pageNum=1&pageSize=5&&msg=queryClassID&b_id=4");
                open("goodsQuery.html",target="body_i");
            });

        });


    </script>

</head>
<body onload="nowdate()">
<div id="head">
    <div id="login">
        <div class="login_a" style="margin-left: 12px;width: 80px">
            <div class="login_b" style="background-position:-3PX 0 "></div>
            <a href="gouwuche.html" target="body_i" class="a_login">购物车</a>
        </div>
        <div class="login_a">
            <div class="login_b" style="background-position:-44PX 0 "></div>
            <a href="help.html" target="body_i" class="a_login">帮助中心</a>
        </div>
        <div class="login_a">
            <div class="login_b" style="background-position:-89PX 0 "></div>
            <a class="a_login" onClick="AddFavorite(window.location,document.title)" href="javascript:void(0)">加入收藏</a>
        </div>
        <div class="login_a">
            <div class="login_b" style="background-position:-130PX 0 "></div>
            <a class="a_login" onClick="SetHome(window.location)" href="javascript:void(0)">设为首页</a>
        </div>
        <div class="login_a" style="width: 100px;margin-left: 5px">
            <a href="login.html" target="body_i">
                <div id="login_d">登录</div>
            </a>
            <a href="register.html" target="body_i">
                <div id="login_z">注册</div>
            </a>
        </div>

    </div>
    <div id="welcome">
        <label><b id="loginName"></b>您好，欢迎您登录贵美商城！</label><label id="shijian"></label><b id="out"><b>退出</b>
    </div>
    <div id="search" style="width:230px;height:30px;float:right;margin:11px 8px 0 0;">
        <input type="text" id="ser" style="width:160px;height:22px;margin:0"><img src="../images/img/head/search.jpg" id="sea" style="float:right;">
    </div>
    <div id="head_dh">
        <a href="homepage.html" target="body_i">
            <div class="head_dh_tap">首页</div>
        </a>
        <div class="head_dh_tap" id="electricAppliance">家用电器</div>
        <div class="head_dh_tap" id="phone">手机数码</div>
        <div class="head_dh_tap" id="baihuo">日用百货</div>
        <div class="head_dh_tap" id="book">书籍</div>
        <a href="help.html" target="body_i">
            <div class="head_dh_tap">帮助中心</div>
        </a>
        <a href="#">
            <div class="head_dh_tap">免费开店</div>
        </a>
        <a href="#">
            <div class="head_dh_tap">全球咨询</div>
        </a>
    </div>
</div>
</body>
</html>
