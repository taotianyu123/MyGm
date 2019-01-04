<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 退出当前系统，必须将session中的数据销毁 -->

<%
	
    //销毁session对象
	session.invalidate();
	//重定向到登录页面
	response.sendRedirect("head.html");
%>
