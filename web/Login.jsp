<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学校后台管理系统登录界面</title>
    <meta name="keywords" content="登录界面,登录界面模板,登录模板,后台登录界面模板" />
    <link href="static/css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
    <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            var $tab_li = $('#tab ul li');
            $tab_li.hover(function(){
                $(this).addClass('selected').siblings().removeClass('selected');
                var index = $tab_li.index(this);
                $('div.tab_box > div').eq(index).show().siblings().hide();
            });
        });
    </script>

    <script type="text/javascript">
        $(function(){
            $(".screenbg ul li").each(function(){
                $(this).css("opacity","0");
            });
            $(".screenbg ul li:first").css("opacity","1");
            var index = 0;
            var t;
            var li = $(".screenbg ul li");
            var number = li.size();
            function change(index){
                li.css("visibility","visible");
                li.eq(index).siblings().animate({opacity:0},3000);
                li.eq(index).animate({opacity:1},3000);
            }
            function show(){
                index = index + 1;
                if(index<=number-1){
                    change(index);
                }else{
                    index = 0;
                    change(index);
                }
            }
            t = setInterval(show,8000);
            //根据窗口宽度生成图片宽度
            var width = $(window).width();
            $(".screenbg ul img").css("width",width+"px");
        });
    </script>
</head>

<script>
    $(function () {
        var msg = "${param.msg}";
        if(msg.length>0){
            if(msg=="0"){
               $(".stu_error_box").html("你输入的学号或密码有误！").css("color","red");
            }else if(msg=="1"){
                $(".stu_error_box").html("不登录不能访问系统资源！").css("color","red");
            }else if(msg=="2"){
                $(".stu_error_box").html("验证码输入有误！").css("color","red");
            }
        }

        $("#stuImgCode").click(function () {
            $(this).prop("src","/imageCode?msg="+Math.random());
        });
        //为教工编号绑定一个失去焦点事件
       /* $("#teaIds").blur(function(){
            var teaIdValue = $(this).val();
            //执行Ajax请求 通过js的方式去访问Servlet资源
            var url = "doTea";
            var data = {
                action:"checkTeaId",
                teaId:teaIdValue
            };
            $.get(url,data,function(responseData){
                //responseData表示从Servlet中返回的文本内容
                if(responseData=="yes"){
                    //id存在
                    $(".tea_error_box").html(" ");
                    $("#teaSubmit").prop("disabled",false)
                }else{
                    //id不存在
                    $(".tea_error_box").html("你输入的教工号不存在！").css("color","red");
                    $("#teaSubmit").prop("disabled",true)
                }
            },"text");
        });
*/

    });
</script>



<body>
<div id="tab">
    <ul class="tab_menu">
        <li class="selected">管理员登录</li>
        <li>商人登录</li>
        <li>教务登录</li>
    </ul>
    <div class="tab_box">
        <!-- 学生登录开始 -->
        <div>
            <div class="stu_error_box"></div>
            <form action="doadmin?action=login" method="post">
                <div>
                    <label>姓&nbsp;&nbsp;&nbsp;名：</label>
                    <input type="text"  name="supname" placeholder="输入姓名"  required/>
                </div>
                <div>
                    <label>密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password"  name="supPassword" placeholder="输入密码"  required />
                </div>
                <div>
                    <input type="checkbox" name="remember">
                    <label>记住密码</label>
                </div>
                <div>
                    <input style="width: 50px"  type="text" name="imgCode"/>
                    <img id="stuImgCode" src="imageCode">
                </div>
                <div>
                    <button type="submit" >登录</button>
                </div>
            </form>
        </div>
        <!-- 学生登录结束-->
        <!-- 导师登录开始-->
        <div class="hide">
            <div class="tea_error_box"></div>
            <form action="doTeacher?status=login" method="post">
                <div >
                    <label>教工号：</label>
                    <input id="teaIds" type="text"  name="teaId" placeholder="输入教工号"  />
                    <!--ajaxurl="demo/valid.jsp"-->
                </div>
                <div>
                    <label>密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password"  name="teaPwd" placeholder="输入密码"  />
                </div>
                <div>
                    <div>
                        <input type="checkbox" name="remember">
                        <label>记住密码</label>
                    </div>
                    <div>
                        <button disabled id="teaSubmit" type="submit">登录</button>
                    </div>
            </form>
        </div>
        <!-- 导师登录结束-->
        <!-- 教务登录开始-->
        <div class="hide">
            <div class="sec_error_box"></div>
            <form action="/teaLoginURL" method="post">
                <div>
                    <label>教务号：</label>
                    <input type="text"  name="username" placeholder="输入教工号" required/>
                    <!--ajaxurl="demo/valid.jsp"-->
                </div>
                <div>
                    <label>密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password" name="password" placeholder="输入密码" required />
                </div>
                <div id="remember">
                    <input type="checkbox" name="remember">
                    <label>记住密码</label>
                </div>
                <div id="login">
                    <button type="submit">登录</button>
                </div>
            </form>
        </div>
        <!-- 教务登录结束-->
    </div>
</div>
<div class="bottom">©2018 北大课工场 <a href="javascript:;" target="_blank">关于</a> <span>桂ICP证*******号</span><img width="13" height="16" src="static/images/copy_rignt_24.png" /></div>
<div class="screenbg">
    <ul>
        <li><a href="javascript:;"><img src="static/images/0.jpg"></a></li>
        <li><a href="javascript:;"><img src="static/images/1.jpg"></a></li>
        <li><a href="javascript:;"><img src="static/images/2.jpg"></a></li>
    </ul>
</div>
</body>
</html>
