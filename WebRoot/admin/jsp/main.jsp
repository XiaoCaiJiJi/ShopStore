<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>购物商城-后台管理</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
  
 <!-- rows 把网页横切成两分,头部文件占80个像素,其他的是中间 -->
 <frameset rows="80,*" framespacing="0" border="0">
  	<!-- top.jsp 头部文件 -->
  	<frame name="title" src="${ctx}/admin/jsp/top.jsp"  scrolling="no" frameborder="0" >
  	<!-- 中间再次纵向切成两个文件,左边占12%,右边占所有 -->
  	<frameset cols="138,*" border="1">
  		<!-- 左边是菜单 -->
    	<frame name="fraLeftFrame" src="${ctx}/admin/jsp/menu.jsp" scrolling="no"  frameborder="0"/>
		<!-- name指给该区域取名字,本系统中给链接显示结果 -->
    	<frame name="fraMainFrame" src="${ctx}/admin/jsp/self.jsp"  frameborder="0">
  	</frameset>
 </frameset>
</html>