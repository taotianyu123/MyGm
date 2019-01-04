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
    <title>购物车</title>
    <link href="../css/guimei.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../js/jquery-2.1.0.js"></script>
    <style type="text/css">
        .tbodyD{
            text-align: center;
            line-height: 20px;
            font-size: 13px;
        }

    </style>
    <script type="text/javascript">
        $(function(){

            var pageNum=1;/* 当前页 */
            var pageSize=5;/* 每页行数 */
            var totalPage=0;/* 总页数 */
            var Path="../servlet/ShoppingCarServlet";
            var action="shoppingCarQurey";
            var value="";




            /* 实现显示数据条数的方法 */
            $("[name='pagesize']").change(function(){
                if($(this).val()==""||$(this).val()==null){
                    pageSize=1;
                }else{
                    pageSize=$(this).val();
                }
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
                show(url);
            });

            /*实现换页的方法 */
            $("button").click(function(){
                /* 判断是否打开的是同一页 */
                if(pageNum==$(this).val()){
                    alert("已经是第"+$(this).val()+"页了");
                }
                pageNum=$(this).val();
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
                show(url);
            });

            /* 跳转页码的实现方法 */
            $("[ type='button']").click(function(){
                pageNum=$("[name='num']").val();
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
                show(url);
            });

            /* 拖动页面的实现方法 */
            $("[ type='range']").change(function(){
                pageNum=$("[type='range']").val();
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
                show(url);
            });



            /* 接收Json文件 实现数据打印的方法 */
            function show(url){
                /* Ajax post 方法加载数据 url请求数据的地址 json是接收数据名*/
                $.post(url,function(json){
                    /* 接收page实体类 当前页的值 */
                    pageNum=json.page.pageNum;
                    /* 接收page实体类 总页数的值 */
                    if(json.page.totalPage==0){
                        alert('没有数据！,如果您还是游客请先注册登录！');
                    }else{
                        totalPage=json.page.totalPage;
                    }
                    /* 分页工具栏 */

                    /* 设置购物车总数 */
                    $("#totalRecord").html(json.page.totalRecord);
                    /* 设置首页 value 值 */
                    $("button:eq(0)").val("1");
                    /* 设置上一页 value 值 在这里用三目运算限制超出页码范围*/
                    $("button:eq(1)").val(json.page.pageNum-1<1?1:json.page.pageNum-1);
                    /* 设置下一页 value 值 在这里用三目运算判限制超出页码范围*/
                    $("button:eq(2)").val(json.page.pageNum+1>json.page.totalPage?json.page.totalPage:json.page.pageNum+1);
                    /* 设置尾页 value 值 */
                    $("button:eq(3)").val(json.page.totalPage);
                    /* 设置跳转窗的最大值 */
                    $("[type='pagenum']").attr("max",json.page.totalPage);
                    /* 投置跳转窗的当前页 */
                    $("[name='pagenum']").val(json.page.pageNum);
                    /* 投置拖动条的最大值 */
                    $("[type='range']").attr("max",json.page.totalPage);
                    /* 投置拖动条的当前页 */
                    $("[type='range']").val(json.page.pageNum);
                    /* 设置当前页码标签 */
                    $("#totalPage").html(json.page.totalPage);
                    /* 设置总页码标签 */
                    $("#pageNum").html(json.page.pageNum);

                    var count_money = 0;
                    var sheng_money = 0;
                    $("#shoppingcar").empty();
                    $.each(json.page.data,function(index,value){
                        count_money += value.s_money * value.s_number;
                        sheng_money += value.s_g_money - value.s_money;
                        $("#shoppingcar").append("<tr class=trs>"+
                            "<td class='tbodyD'><input type='checkbox' checked value='"+value.s_id+"'></td>"+
                            "<td class='tbodyD'style='text-align:left;'>"+value.s_g_name+
                            "</td><td class='tbodyD'><img src='../images/img/tejia/"+value.s_g_img+"' width='80px' height='80px'>"+
                            "</td><td class='tbodyD'>"+value.s_g_money+"元"+
                            "</td><td class='tbodyD'>"+value.s_money+"元"+
                            "</td><td class='tbodyD'>"+value.s_d_disc+
                            "</td><td class='tbodyD'><input type='number'min='1' style=width:28px value="+value.s_number+
                            " onchange='UpdateSnumber("+value.s_id+
                            ",this.value)'/>件</td><td class='tbodyD'><b onclick=delshoppingcar("+value.s_id+
                            ")>删除</b></td></tr>");
                    });

                    $("#count_money").html(count_money);
                    $("#sheng_money").html(sheng_money);
                    /* 反回数据类型 */

                },'json');
            }
            var url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
            show(url);










            /* 订单提交事件 */
            $("#imgsubmit").click(function(){
                var s_id = [];
                for (var i = 0; i <$("[type='checkbox']:checked").length; i ++) {
                    s_id.push($("[type='checkbox']:checked:eq("+i+")").val());
                }
                location="../servlet/orderManagerServlet?action=orderManagersubmint&s_id="+s_id+"&o_address="+
                    "姓名:"+$("[name='name']").val()+","+
                    "地址:"+$("[name='address']").val()+","+
                    "电话:"+$("[name='quhao']").val()+"-"+$("[name='phone']").val()+"-"+$("[name='fenji']").val()+","+
                    "手机:"+$("[name='telpone']").val();
            });
        });
        /* 通过ID删除购物车项目 */
        function delshoppingcar(s_id) {
            if(confirm("您确定要删除吗？")){
                location="../servlet/ShoppingCarServlet?action=shoppingCarDelete&s_id="+s_id;
            }
        }

        /* 通过ID修改购物车商品数目 */
        function UpdateSnumber(s_id,s_number){
            location="../servlet/ShoppingCarServlet?action=shoppingCarUpdateByID&s_id="+s_id+"&s_number="+s_number;
        }



    </script>

</head>
<body>

<div id="wodeguc">
    <img src="../images/img/images/mycart.gif">
    <label class="wudeguclabel">全场满100免运费</label>
    <label class="wudeguclabel">简易计算器</label>
</div>

<div id="quanren"  >
    <p id="wodeguc_2jibiaoti">确认商品价格与交易条件</p>
    <table border="0px" width="95%"style="margin-left:30px;">
        <thead style="font-size: 16px;color:red;line-height:65px; text-align: center; ">
        <tr id="quanrentable_hang1">
            <td style="width:4%;"><input type="checkbox" name="q1"></td>
            <td style="width:36%;text-align:left;">商品名</td>
            <td style="width:20%;">图片</td>
            <td style="width:8%;">原价</td>
            <td style="width:8%;">优惠价</td>
            <td style="width:8%;">打折</td>
            <td style="width:8%;">数量</td>
            <td style="width:8%;">操作</td>
        </tr>
        </thead>
        <tbody id="shoppingcar"></tbody>
    </table>
    <table id="paging" border="0px" width="95%" style="margin-left:30px;margin-top:30px;text-align:left;" >
        <tr>
            <td width="50px"><button>首页</button></td>
            <td width="60px"><button>上一页</button></td>
            <td width="100px"><input type="number" min="1" style="width:45px" name="pagenum"><input type="button" value="跳转"></td>
            <td width="60px"><button>下一页</button></td>
            <td width="50px"><button>尾页</button></td>
            <td>
                滑动翻页：<input type="range" min="1">
            </td>
            <td style="text-align:right;">
                当前第<b id="pageNum"></b>页，共<b id="totalPage"></b>页&nbsp;
                总数：<b id="totalRecord"  ></b>条
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="5">
                <p >优惠金额￥:<b id="sheng_money"></b></p>
            </td>
            <td>
                <p >应付金额￥:<b id="count_money"></b></p>
            </td>
            <td style="text-align: right;">
                <p><a href="homepage.html">返回继续购买商品</a></p>
            </td>
        </tr>
    </table>
</div>
<div id="dizhi">
    <p id="dizhi_2jibiaoti">补充您的邮件地址和联系人信息</p>
    <br/>
    姓名：<input type="text" name="name" value="张拓"><br>
    地址：<input type="text" name="address" value="湖南长沙岳麓区溁湾填"><br>
    电话：<input type="text" size="4" name="quhao" value="0731">-<input type="text" size="8" name="phone" value="88858332">-<input type="text" size="4" name="fenji" value="8888">
    （区号-电话号码-分机）<br>
    手机：<input type="text" size="11" name="telpone" value="15874073679"><br>
    <img id="imgsubmit"  src="../images/img/zhuce/submit.gif">

</div>

</body>
</html>