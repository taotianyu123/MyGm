<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\1\3 0003
  Time: 14:42
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


        //实现 单选/全选 按钮关联
        var li=document.getElementsByName("Xx");

        //全选
        function quanx(){
            for(var x in li){
                li[x].checked=li[0].checked;
            }
        }

        //关联全选
        function danx(){
            for(var x=1;x<li.length;x++) {
                if (!li[x].checked) {
                    li[0].checked = li[x].checked;
                    return;
                }
                li[0].checked=li[x].checked;
            }
        }

        $(function(){

            var g_sm_id=0;
            var pageNum=1;/* 当前页 */
            var pageSize=5;/* 每页行数 */
            var totalPage=0;/* 总页数 */
            var Path="../servlet/goodsManagerServlet";
            var action="queryGoodsByClassid";


            /* 页面刷新的方法*/
            function Reload (){
                location.reload();
            }

            /* 实现显示数据条数的方法 */
            $("[name='pagesize']").change(function(){
                if($(this).val()==""||$(this).val()==null){
                    pageSize=1;
                }else{
                    pageSize=$(this).val();
                }
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&g_sm_id="+g_sm_id;
                show(url);
            });

            /*实现换页的方法 */
            $("button").click(function(){
                /* 判断是否打开的是同一页 */
                if(pageNum==$(this).val()){
                    alert("已经是第"+$(this).val()+"页了");
                }
                pageNum=$(this).val();
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&g_sm_id="+g_sm_id;
                show(url);
                setTimeout(Reload,300);
            });

            /* 跳转页码的实现方法 */
            $("[ type='button']").click(function(){
                pageNum=$("[name='pagenum']").val();
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&g_sm_id="+g_sm_id;
                show(url);
                setTimeout(Reload,300);
            });

            /* 拖动页面的实现方法 */
            $("[ type='range']").change(function(){
                pageNum=$("[type='range']").val();
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&g_sm_id="+g_sm_id;
                show(url);
                setTimeout(Reload,300);
            });



            /* 接收Json文件 实现数据打印的方法 */
            function show(url){
                /* Ajax post 方法加载数据 url请求数据的地址 json是接收数据名*/
                $.post(url,function(json){
                    /* 接收page实体类 当前页的值 */
                    pageNum=json.page.pageNum;
                    /* 接收page实体类 总页数的值 */
                    if(json.page.totalPage==0){
                        alert('没有数据！');
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

                    $("#shoppingcar").empty();
                    $.each(json.page.data,function(index,value){
                        g_sm_id=value.g_sm_id;
                        $("#shoppingcar").append("<tr class='trs' onclick='goodsDetail("+value.g_id+")'>"+
                            "<td class='tbodyD'style='text-align:left;'>"+value.g_name+
                            "</td><td class='tbodyD' style='text-align: center;'><img src='../images/img/tejia/"+value.g_img+"' width='80px' height='80px'>"+
                            "</td><td class='tbodyD' style='text-align: center;'>"+value.g_money+"元"+
                            "</td><td class='tbodyD' style='text-align: center;'>"+value.g_number+"件"+
                            "</td></tr>");
                    });
                    /* 反回数据类型 */
                },'json');

            }
            var url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&g_sm_id="+g_sm_id;
            show(url);


        });


        /* 单击商品详情 */
        function goodsDetail(g_id){
            location = "../servlet/goodsManagerServlet?action=queryGoodsByid&g_id="+g_id;
        }




    </script>
</head>
<body>
<div id="dzcpbody">
    <table border="0px" width="95%">
        <thead style="font-size: 16px;color:red;line-height:35px; text-align: center; ">
        <tr id="quanrentable_hang1">
            <td style="width:36%;text-align:left;">商品名</td>
            <td style="width:20%;">图片</td>
            <td style="width:8%;">原价</td>
            <td style="width:8%;">数量</td>
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
    </table>




</div>
</body>
</html>
