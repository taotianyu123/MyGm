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
    <script>
        $(function(){
            $(".info").css("font-size","11px");

            var loginname=/^[A-Za-z0-9_$]{1,20}$/;
            var C_loginName="no";
            var Pwd=/^[A-Za-z0-9_$]{6,20}$/;
            var C_pwd="no";

            /*密码正则*/
            $("[name='C_pwd']").blur(function(){
                if(Pwd.test($(this).val())){
                    C_pwd="ok";
                    $(this).css("background-color","#3EFF8D");
                }else{
                    C_pwd="no";
                    $(this).css("background-color","#FFCCFF");
                }
            });

            /*登录名正则验证 */
            $("input[name='C_loginName']").blur(function(){
                if(loginname.test($(this).val())){
                    C_loginName="ok";
                    $("[name='C_loginName']").css("background-color","#3EFF8D");
                }else{
                    C_loginName="no";
                    $("[name='C_loginName']").css("background-color","#FFCCFF");
                }
            });

            /* 正则 提交 */
            $("form").submit(function(){
                if(C_loginName=="ok"&&C_pwd=="ok") {
                    return true;
                } else {
                    alert("您输入的账户密码格式不正确！");
                    return false;
                }
            });
        });



    </script>
</head>
<body>
<div id="login_1">
    <div id="login_left_d">
        <img src="../images/img/login/logintitle.gif">
        <form action="../servlet/customServlet?action=customLogin" method="post"  ><!-- 表单按钮控制 -->
            <table id="login_left_table" >
                <tr>
                    <td>会员名：<br><label class="info">&nbsp;</label></td>
                    <td><input type="text"  name="C_loginName"><br><label class="info">（可包含a-z、0-9和下划线）</label></td>
                </tr>
                <tr style="height: 40px">
                    <td>密&nbsp;&nbsp;码：<br><label class="info">&nbsp;</label></td>
                    <td><input type="password" name="C_pwd" ><br><label  class="info">（至少包含6个字符）</label></td>
                </tr>
            </table>
            <br>
            <input type="image" style="margin-left:100px" src="../images/img/login/login.gif"/>
        </form>
        <p class="login_left_P">什么是安全登录。</p>
        <p class="login_left_P">香港会员（繁体中文用户）由些登入</p>
        <p class="login_left_P">密码安全贴士。</p>
        <p class="login_left_P">防止病毒或者木马窃取您的账户信处，在线检查你的电脑是否安全。</p>
    </div>
    <div id="login_right_d">
        <div id="login_right_d_top">
            <p id="login_right_d_biaoti">注册会员</p>
            <div class="login_right_d_tubiao" id="login_right_d_biaoti1"></div>
            <div><label class="login_right_d_biaoti1_colorbule">便宜有好货！</label> 超过7000万件商品任您选。</div>
            <div class="login_right_d_tubiao" id="login_right_d_biaoti2"></div>
            <div><label class="login_right_d_biaoti1_colorbule">买卖更安全！</label> 交易超安全。</div>
            <div class="login_right_d_tubiao" id="login_right_d_biaoti3"></div>
            <div><label class="login_right_d_biaoti1_colorbule">免费开网店！</label> 轻松赚钱交友。</div>
            <div class="login_right_d_tubiao" id="login_right_d_biaoti4"></div>
            <div><label class="login_right_d_biaoti1_colorbule">超人所社区！</label> 精彩活动每一天</div>
            <center><img src="../images/img/login/registernow.gif"></center>
        </div>
        <div id="login_right_d_di">
            <div id="login_right_d_di_kuang">
                <div id="login_right_d_di_hang1">
                    <div id="login_right_d_di_hang1_tu1"></div>
                    <div class="login_right_d_p">您已经是会员？由此登入</div>
                </div>
                <div id="login_right_d_di_hang2">
                    <div id="login_right_d_di_hang1_tu2"></div>
                    <div  class="login_right_d_p">繁体中文用户由此注册</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
