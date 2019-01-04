		/*正则表达式 script*/
        var Pwd=/^[A-Za-z0-9_$]{6,20}$/;
        var loginname=/^[A-Za-z0-9_$]{1,20}$/;
        var userid=/^\d{18,18}$/;
     	var names=/^[\u2E80-\u9FFF]{1,}|\w{1,}$/;
     	var email=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
     	
        var clause="no";
        var C_name="no";
        var C_pwd="no";
        var C_loginName="no";
        var C_code="no";
        var C_email="no";
        var C_hobby="no";
        var C_file="no";
        var C_birthday="no";
        var format="no";
       
        
       $(function(){
    	   /*网络服务协议*/
    	   $("input").attr("disabled",true);
    	   $("input[name='clause']").attr("disabled",false);
    	   $("input[name='clause']").click(function(){
    		   if($("input[name='clause']:checked").length>0){
   				clause="ok";
   				$("input").attr("disabled",false);
   				$("[name='C_pwd2']").attr("disabled",true);
    		   }else{
    	    	   $("input").attr("disabled",true);
    	    	   $("input[name='clause']").attr("disabled",false);
    	    	   clause="no";
    		   }
    	   });
    	   
    	   
			
       		/*姓名正则*/
       		$("[name='C_name']").change(function(){
       			if(names.test($(this).val())){
       				C_name="ok";
       		    	$(this).css("background-color","#3EFF8D");
				}else{
					C_name="no";
                	$(this).css("background-color","#FFCCFF");
				}
       		});
       		
       		/*密码正则*/
       		$("[name='C_pwd']").change(function(){
       			if(Pwd.test($(this).val())){
       		    	$(this).css("background-color","#3EFF8D");
       		    	$("[name='C_pwd2']").attr("disabled",false);
				}else{
                	$(this).css("background-color","#FFCCFF");
                	$("[name='C_pwd2']").attr("disabled",true);
				}
       		});
       		
       		//密码验证
       		$("[name='C_pwd2']").change(function(){
       			if($(this).val()==$("[name='C_pwd']").val()&&$(this).val()!=""){
       		    	$(this).css("background-color","#3EFF8D");
       		    	C_pwd="ok";
				}else{
                	$(this).css("background-color","#FFCCFF");
                	C_pwd="no";
				}
       		});
       		       		
    		/*登录名数据库验证 登录名正则验证 */
    		$("input[name='C_loginName']").change(function(){
    			var url = "../servlet/customServlet";
    			$.post(url, 
    				{"action":"checkName","nameValue":$("[name='C_loginName']").val()},
    				function(data){
    	       			if(loginname.test($("[name='C_loginName']").val())){
    	       		    	if(data=="yes"){
        						$("#checkName").html("恭喜,该用户名可以使用！").css({"font-size":"11px",color:"green"});
        						C_loginName="ok";
        						$("[name='C_loginName']").css("background-color","#3EFF8D");
        					}else{
        						$("#checkName").html("抱歉,该用户名已经被注册！").css({"font-size":"11px",color:"red"});
        						C_loginName="no";
        						$("[name='C_loginName']").css("background-color","#FFCCFF");
        					}
    					}else{
    	                	$("[name='C_loginName']").css("background-color","#FFCCFF");
    	                	C_loginName="no";
    					}

    				},"text");
    		});
       		
    		/*身份证正则*/
       		$("[name='C_code']").change(function(){
       			if(userid.test($(this).val())){
       		    	$(this).css("background-color","#3EFF8D");
                	C_code="ok";
				}else{
                	$(this).css("background-color","#FFCCFF");
                	C_code="no";
				}
       		});
       		

       		/*邮箱正则*/
       		$("[name='C_email']").change(function(){
       			if(email.test($(this).val())){
                	C_email="ok";
                	$(this).css("background-color","#3EFF8D");
				}else{
                	C_email="no";
                	$(this).css("background-color","#FFCCFF");
				}
       		});
       		

       		       		       		
   			/*图片格方的判断方法*/
			$("input[type='file']").change(function(){
				
		    	var fileName = $(this).val();
	    		var filetype = fileName.substring(fileName.lastIndexOf("."));
	    		if(filetype.toLowerCase()!=".jpg"){
	    			alert("请使用JPG文件");
	    			$("#filetext").html("&nbsp;× 只支持JPG格式");
	    			format="no";
					$("#textfield").css("background-color","#FFCCFF");
                	C_file="no";
	    		}else{
	    			$("#filetext").html("&nbsp; √ 恭喜，格式正确 ");
	    			format="ok";
       				$("#textfield").css("background-color","#3EFF8D");
	       			C_file="ok";
	    		}
	    	});
			
			/*爱好不为空*/
			$("input[name='C_hobby']").blur(function(){
				if($("input[name='C_hobby']:checked").length>0){
					C_hobby="ok";
					$(this).css("background-color","#3EFF8D");
				}else{
					C_hobby="no";
					$(this).css("background-color","#FFCCFF");
				}
			});
			
			/*日期不为空*/
			$(".easyui-datebox").datebox({
				onSelect: function(){
					if($("input[name='C_birthday']").val()!=""){
						C_birthday="ok";
						$(".validatebox-text:eq(5)").css("background-color","#3EFF8D");
					}
					if($("input[name='C_birthday']").val()==""){
						C_birthday="no";
						$(".validatebox-text:eq(5)").css("background-color","#FFCCFF");
					}
				}
			});
			
	 		/*script 提交按扭控制*/
	        $("form").submit(function(){
	    		if(C_hobby=="ok"&&C_name=="ok"&&C_pwd=="ok"&&format=="ok"&&clause=="ok"&&
		    	C_loginName=="ok"&&C_code=="ok"&&C_email=="ok"&&C_birthday=="ok"&&C_file=="ok") {
	   				return true;
		    	}else{
		    		alert("请完整填写您的基本资料！");
					return false;
		    	}
	   		});

       });
       		

				
		
		