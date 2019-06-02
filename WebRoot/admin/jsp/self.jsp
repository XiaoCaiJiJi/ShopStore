<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>购物商城</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link href="../css/commons.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		body{margin:0px 0px 0px 0px;background-color:#fff;}
	</style>
  </head>
  <body>
  	<div style="width:100%;height:20px;background-color:#eee;margin-bottom:10px;"></div>
    <div align="center">
		<fieldset style="width:500px;">
			<table width="450" align="center" style="font-size:14px;">
				<tr align="left">
					<td>用&nbsp;户：</td>
					<td>${session_user.name}</td>
				</tr>
				<tr align="left">
					<td>性&nbsp;别：</td>
					<td>
						<c:if test="${session_user.sex ==1}">男</c:if>
						<c:if test="${session_user.sex ==2}">女</c:if>
						
					</td>
				</tr>
				<tr align="left">
					<td>手&nbsp;机：</td>
					<td>${session_user.phone}</td>
				</tr>
				<tr align="left">
					<td>邮&nbsp;件：</td>
					<td>${session_user.email}</td>
				</tr>
				<tr align="left">
					<td>地&nbsp;址：</td>
					<td>${session_user.address}</td>
				</tr>
			</table>
		</fieldset>
	</div>
  </body>
</html>