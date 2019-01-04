    var Stoptime;	//延时对象
    var cindex=1;	//存储 选中的图片 下标
	var xindex=0;	//存储 循环的图片 下标
		
		
		//自动加截 function
        window.onload=function(){
            xunhuan();
        };

		//广告图片 3秒循环展示
        function xunhuan(index){
		//传入要显标图片数组的下标 index
		
			//获取Name 为adimg 的图片数组
         var adimgarr=document.getElementsByName("adimg");
            cindex = index;
			
			if(Number(cindex)){ //Number() 判断是不是数字，它判断0不为数字，这里使用了这个特性
			xindex = index;//赋值给循环下标让他接上选中图片后循环
                for (var i=0;i<adimgarr.length;i++) {
                    adimgarr[i].style.display = (i == cindex-1)?"block":"none";
					//循环图片数组 三目运算 cindex对应的图片 block 其它设为 none
                }
                clearTimeout(Stoptime);// 每次调用 需先清除计时器
				cindex=0; //让它为零，循环后则会进入else
            }else{
                for (var i=0;i<adimgarr.length;i++) {
                    adimgarr[i].style.display = (i == xindex)?"block":"none";
					//循环图片数组 三目运算 xindex对应的图片 block 其它设为 none
                }
				//循环图片下标
                if(xindex==3) {
                    xindex=0;
                }else{
                    xindex++;
                }
            }
			//放在if 外面不管是 执行哪个都行成循环
			//延时3000毫秒 调用 xunhuan();
            Stoptime=setTimeout(xunhuan,3000);
        }
        

        
        /* 单击商品详情 */
      function goodsDetail(g_id){
        	location = "../servlet/goodsManagerServlet?action=queryGoodsByid&g_id="+g_id;
        }
		
		// 充值框的隐藏显示
      function congzi(){
            var adimg=document.getElementById("ad_img");
            var congzi=document.getElementById("b3congzhi");
            var congzi2=document.getElementById("b3congzhi2");


            if(adimg.style.visibility=="visible"){
                adimg.style.visibility="hidden";
                congzi.style.display="block";
                congzi2.style.display="none";
            }else{
                adimg.style.visibility="visible";
                congzi.style.display="none";
                congzi2.style.display="block";

            }
		}
      
      
      
      
  	$(function(){
		var pageNum=1;/* 当前页 */
		var pageSize=9;/* 每页行数 */
		var totalPage=0;/* 总页数 */ 
		var Path="../servlet/goodsManagerServlet";
		var action="goodsManagerVagueQurey";
		var value="";

			/* 拖动页面的实现方法 */
			$("[ type='range']").change(function(){
				pageNum=$("[type='range']").val();
				url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
				show(url);
			});

		
		/* 公告栏 请求 */
	  		function announshow() {
			$.post(
				"../servlet/annManagerServlet?action=annManagerVagueQurey&pageNum=1&pageSize=3&value=",
				function(json){
					$.each(json.page.data,function(index,value){
						$("#announ").append("<marquee direction=left scrollamount=6><b style='color:red;'>"+value.a_title+"</b>&nbsp;"+value.a_text+"</marquee><br/>");
					});
				},"json");
				State=1;
				if(State==1){
					setTimeout(classshow,500); 
				}
		} 	 	 
		
		/* 分类器选择 */
		var a=1;
	  		function classshow () {
			$.post(
				"../servlet/smallclassServlet?action=getsmallClassData",
				function(json){
					$.each(json,function(index,value){
						if(a<16){
							$("#class_4_small").append("<div><a href='../servlet/goodsManagerServlet?"+
							"action=queryGoodsByClassid&pageNum=1&pageSize=5&g_sm_id="+value.sm_s_id+"'>"+value.sm_s_name+"</a></div>");
							a++;
						}
						
						if(a>16&&a<32){
							$("#class_3_small").append("<div><a href='../servlet/goodsManagerServlet?"+
							"action=queryGoodsByClassid&pageNum=1&pageSize=5&g_sm_id="+value.sm_s_id+"'>"+value.sm_s_name+"</a></div>");
							a++;
						}
						if(a>32&&a<48){
							$("#class_2_small").append("<div><a href='../servlet/goodsManagerServlet?"+
							"action=queryGoodsByClassid&pageNum=1&pageSize=5&g_sm_id="+value.sm_s_id+"'>"+value.sm_s_name+"</a></div>");
							a++;
						}
						if(a>48&&a<64){
							$("#class_1_small").append("<div><a href='../servlet/goodsManagerServlet?"+
							"action=queryGoodsByClassid&pageNum=1&pageSize=5&g_sm_id="+value.sm_s_id+"'>"+value.sm_s_name+"</a></div>");
							a++;
						}
						if(a==16){$("#class_4_big").html(value.sm_b_name);a++;}
						if(a==32){$("#class_3_big").html(value.sm_b_name);a++;}
						if(a==48){$("#class_2_big").html(value.sm_b_name);a++;}
						$("#class_1_big").html(value.sm_b_name);
						
					});
				},"json");
				State=2;
				if(State==2){
					setTimeout(newgoods,500);
				} 
		}; 
		
		var State=0;     /* Ajax加载状态 */
	    /* 商品页面循环 */
		function goodsCycle (){
	    		if(pageNum>=totalPage){
	      			pageNum=1;
	    		}else{
	    			pageNum++;
	    		}
			
			url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
			show(url);
			Stoptime=setTimeout(goodsCycle,5000);
			}
		
		/* 接收Json文件 实现数据打印的方法 */
		function show (url){
			
			/* Ajax post 方法加载数据 url请求数据的地址 json是接收数据名*/
			$.post(url,function(json){
				/* 接收page实体类 当前页的值 */
				pageNum=json.page.pageNum;
				/* 接收page实体类 总页数的值 */
				totalPage=json.page.totalPage;
				/* 分页工具栏 */
				/* 投置拖动条的最大值 */
				$("[type='range']").attr("max",json.page.totalPage);
				/* 投置拖动条的当前页 */
				$("[type='range']").val(json.page.pageNum);
				/* 设置当前页码标签 */
				$("#totalPage").html(json.page.totalPage);
				/* 设置总页码标签 */
				$("#pageNum").html(json.page.pageNum);
				

				$("#goodsShow").empty();
				//循环list集合 加载买家数据
				$.each(json.page.data,function(i,v){
					$("#goodsShow").append("<dl class='b2dte' onclick='goodsDetail("+v.g_id+
					")'><dt><img src='../images/img/tejia/"+v.g_img+
					"' width='135px' height='85px'><dt><dd style='font-size:11px;'>"+v.g_name+
					"<dd><dl>");
				});
			},"json");	/* 反回数据类型 */	
			State=4;
		}
		
		var url = Path+"?action="+action+"&pageNum="+pageNum+"&pageSize="+pageSize+"&value="+value;
		
		setTimeout(announshow,500); 
		
		
		
		
		/* 新品 请求 */
  		function newgoods() {
		$.post(
			"../servlet/goodsManagerServlet?action=newGoodsQuery",
			function(json){
				$.each(json,function(index,value){
					$(".b3dx").append("<div class='b3dx1' onclick=goodsDetail("+value.g_id+")><img class='b3dimg' src='../images/img/tejia/"+value.g_img+"'/><label>"+value.g_name+"</label></div>");
				});
			},"json");
		setTimeout(goodsCycle,500);
	} 	 	 
	
		
		
		
		
		
		
		
		
		
		
  });