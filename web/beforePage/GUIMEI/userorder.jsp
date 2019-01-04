<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\1\3 0003
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的贵美</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../EasyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../EasyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../EasyUI/themes/metro/demo.css">
    <script type="text/javascript" src="../EasyUI/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="../EasyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../EasyUI/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">

        $(function(){

            var pageNum=1; /* 当前页 */
            var pageSize=5; /* 每页行数 */
            var totalPage=1; /* 总页数 */
            var Path="../servlet/orderManagerServlet";
            var action="orderVagueQureyByID";
            var value="";


            obj = {
                editRow : undefined,
                /* 搜索的方法 */
                search : function () {
                    pageNum=1;
                    value=$.trim($('input[name="search"]').val());
                    url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
                    show(url);
                },
                /* 添加的方法 */
                add : function () {
                    if (this.editRow != undefined) {
                        $('#Table').datagrid('endEdit', obj.editRow);
                    }
                    $('#save,#redo').show();
                    //添加一行
                    $('#Table').datagrid('insertRow', {
                        index : 0,
                        row : {	},
                    });
                    //将第一行设置为可编辑状态
                    $('#Table').datagrid('beginEdit', 0);
                    this.editRow = 0;
                },
                /* 保存的方法 */
                save : function () {
                    $('#Table').datagrid('endEdit', this.editRow);
                },
                /* 撤销的方法 */
                redo : function () {
                    $('#save,#redo').hide();
                    this.editRow = undefined;
                    $('#Table').datagrid('rejectChanges');
                },
                /* 编辑的方法 */
                edit : function () {
                    var rows = $('#Table').datagrid('getSelections');
                    if (rows.length == 1) {
                        if (this.editRow != undefined) {
                            $('#Table').datagrid('endEdit', this.editRow);
                        }

                        var index = $('#Table').datagrid('getRowIndex', rows[0]);
                        $('#save,#redo').show();
                        $('#Table').datagrid('beginEdit', index);
                        this.editRow = index;
                        $('#Table').datagrid('unselectRow', index);

                    } else {
                        $.messager.alert('警告', '修改必须或只能选择一行！', 'warning');
                    }
                },
                /* 删除的方法 */
                remove : function () {
                    action="orderManagerDelete";
                    /* 获取选择的行 */
                    var row = $('#Table').datagrid('getSelections');
                    /* 判断有选择一行以上 */
                    if (row.length > 0) {
                        $.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
                            if (flag) {
                                var ids = [];
                                for (var i = 0; i < row.length; i ++) {
                                    ids.push(row[i].id);
                                }
                                $.ajax({
                                    type : 'POST',
                                    url :  Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value,
                                    data : {
                                        o_id : ids.join(',')
                                    },
                                    beforeSend : function () {
                                        $('#Table').datagrid('loading');
                                    },
                                    success : function (data) {
                                        if (data>0) {
                                            $('#Table').datagrid('loaded');
                                            $.messager.show({
                                                title : '提示',
                                                msg : data+'个订单被删除成功！',
                                            });
                                            $('#Table').datagrid('unselectAll');
                                            show(url);
                                            action="annManagerVagueQurey";
                                        }
                                    },
                                });
                            }
                        });
                    } else {
                        $.messager.alert('提示', '请选择要删除的记录！', 'info');
                    }
                },
            };



            /* 创建数据列表 */
            $("#Table").datagrid({
                title:'我的贵美——订单列表',
                remoteSort:false,
                fit:true,
                fitColumns:true,
                rownumbers : true,
                autoRowHeight:false,
                toolbar:'#paging',
                scrollbarSize:0,
                columns:[[
                    {field:'id',checkbox : true},
                    {field:'o_id',sortable:true,title:'订单编号',align:'center'},
                    {field:'g_name',sortable:true,title:'商品名称'},
                    {field:'c_name',sortable:true,title:'客户姓名'},
                    {field:'o_money',sortable:true,title:'订单金额'},
                    {field:'o_address',sortable:true,title:'地址',width:100},
                    {field:'o_date',sortable:true,title:'订单日期'},
                    {field:'o_statment',sortable:true,title:'订单状态'},
                ]],

            });

            /* 实现显示数据条数的方法 */
            $("input[name='pagesize']").change(function(){
                if($(this).val()==""||$(this).val()==null){
                    pageSize=1;
                }else{
                    pageSize=$(this).val();
                }
                url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
                show(url);
            });

            /* 实现换页的方法 */
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
                pageNum=$("[type='number']").val();
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
            function show (url){
                /* Ajax post 方法加载数据 url请求数据的地址 json是接收数据名*/
                $.post(url,function(json){
                    /* 接收page实体类 当前页的值 */
                    pageNum=json.page.pageNum;
                    /* 接收page实体类 总页数的值 */
                    if(json.page.totalPage==0){
                        $.messager.alert('警告', '没有数据！游客请先注册登录', 'warning');
                    }else{
                        totalPage=json.page.totalPage;
                    }
                    /* 分页工具栏 */
                    /* 设置显示数据的条数 */
                    $("input[name='pagesize']").val(json.page.pageSize);
                    /* 设置数据的最大条数 */
                    $("input[name='pagesize']").attr("max",json.page.totalRecord);
                    /* 设置首页 value 值 */
                    $("button:eq(0)").val("1");
                    /* 设置上一页 value 值 在这里用三目运算限制超出页码范围*/
                    $("button:eq(1)").val(json.page.pageNum-1<1?1:json.page.pageNum-1);
                    /* 设置下一页 value 值 在这里用三目运算判限制超出页码范围*/
                    $("button:eq(2)").val(json.page.pageNum+1>json.page.totalPage?json.page.totalPage:json.page.pageNum+1);
                    /* 设置尾页 value 值 */
                    $("button:eq(3)").val(json.page.totalPage);
                    /* 投置跳转窗的最大值 */
                    $("[name='pagenum']").attr("max",json.page.totalPage);
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

                    var rows= new Array();/* 加载数组 */
                    //循环list集合 加载买家数据
                    $.each(json.page.data,function(i,v){
                        rows.push({
                            id:v.o_id,
                            o_id:v.o_id,
                            g_name:v.g_name,
                            c_name:v.c_name,
                            o_money:v.o_money,
                            o_address:v.o_address,
                            o_date:v.o_date,
                            o_statment:v.o_statment==0?'未发货':'已发货'
                        });
                    });
                    /* Ajax不刷新 先清空表格 */
                    /* $("#Table").datagrid("loadData",{total: 0, rows:[]});  */
                    /* easyui 加载数据的方法 */
                    $('#Table').datagrid({data:rows});

                    /* 反回数据类型 */
                },'json');
            }

            var url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
            show(url);

        });
    </script>
    <style>
        .textbox{
            height:20px;
            margin:0;
            padding:0 2px;

        }
    </style>
</head>
<body style="padding:5px; width:980px;height:640px;">


<table id="paging" border="0px" width="100%"  style="text-align:left;">
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
            显示条数：<input type="number" min="1" step="4" style="width:42px" name="pagesize"  >
        </td>
    </tr>
    <tr>
        <td colspan="5">
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj.remove();">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" style="display:none;" id="save" onclick="obj.save();">保存</a>
        </td>
        <td>
            <input type="text" class="textbox" name="search" style="width:150px">&nbsp;
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" style="height:20px" onclick="obj.search();">查询</a>
        </td>
        <td style="text-align:right;">当前第<b id="pageNum"></b>页，共<b id="totalPage"></b>页</td>
    </tr>
</table>
<table id="Table"></table>


</body>
</html>
