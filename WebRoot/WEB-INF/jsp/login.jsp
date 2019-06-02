<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>购物商城-登录页面</title>
		<link href="longIt.ico" rel="shortcut icon" type="image/x-icon"/>
		<!-- login.css作用于登录的CSS -->
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
		<script type="text/javascript">
		
		
		$(function(){
			   //为登录按钮绑定事件
			   $("#btnSignCheck").click(function(){
				   
				   //获取用户名
				   var loginName = $("#loginName").val();
				   //获取密码
				   var password = $("#password").val();
				   if(!$.trim(loginName)){
					   $("#mess").html("请输入登录名！");
					   $("#loginName").focus();
				   }else if(!/^[0-9a-zA-Z@.]{5,16}$/.test(loginName)){
					   $("#mess").html("您输入登录名不合法！");
					   $("#loginName").focus();
				   }else if(!$.trim(password)){
					   $("#mess").html("请输入密码！");
					   $("#password").focus();
				   }else if(!/^[0-9a-zA-Z@]{5,16}$/.test(password)){
					   $("#mess").html("您输入密码不合法！");
					   $("#password").focus();
				   }else{
					   //提交表单
					   $("#loginform").submit();
				   }
				   
			   })
		})
		
		
		</script>
	</head>

<body>
	<!--header begin-->
	<div id="shortcut">
		<script type="text/javascript">header("");</script>
	﻿	<div class="nav">
			<div class="w960 center">
				<ul>
					<li><a title="首页" href="index.action">首页</a></li>
					
					<c:forEach items="${firstTypes}" var="firstType">
					   	<li><a title="${firstType.name}" href="${ctx}/index.action?typeCode=${firstType.code}">${firstType.name}</a></li>
					</c:forEach>
					
				</ul>
			</div>
		</div>
	</div>
	<!--header end-->
	
	<form name="loginform" method="post" action="${ctx}/login.action" id="loginform">
		<br />
		<!-- 定义标识符，告诉后台java代码，该请求为用户登录请求 -->
		<input type="hidden" name="flag" value="userLogin"/>
		<div class="login_main" style="background: url(images/login_bg.jpg) no-repeat center 0;">
			<div class="login_panel">
				<!--登录框开始-->
				<div class="right_panel">
					<div class="login_frame_border">
						<div class="login_frame">
							<div class="login_title">
								<font color="red" id="mess">${message}</font> 还不是商城用户？&nbsp;&nbsp;<a href="register.action">立即注册</a>
							</div>
							<div class="login_frist clearfix" id="user_div_name">
								<label>登录名</label> 
								<span class="login_input"> 
									<input name="loginName" type="text" maxlength="40" id="loginName" class="tip"/>
								</span> 
								<span class="hint" id="login_loginname_error" style="display: none"></span> 
								<span class="hint" id="usernameMessage" style="display: black">请输入邮箱地址</span>
							</div>
							<p class="clearfix" id="password_div">
								<label>密&nbsp;&nbsp;码</label> 
								<span class="login_input">
									<input name="password" type="password" maxlength="20" id="password"/>
								</span> 
								<span class="hint" id="login_password_error" style="display: none"></span> 
								<span class="hint" id="passwordMessage" style="display: black">请输入6-16位密码</span>
							</p>

							<div class="login_btn" id="submit_signin_div">
								<input id="btnSignCheck" type="button" value="登 录" style="width: 94px; height: 27px; float: left; 
									background: url(${ctx}/images/login_btn.png) no-repeat -237px -46px;"/>
									&nbsp;&nbsp;
							</div>

							<div class="co_login">
								<span>学习是最好的投资</span><a class="icon_sina"
									id="open_sina_sign" href="" title="新浪微博">软件十年</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--登录框结束-->
</body>
</html>