 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>购物商城-会员注册</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述"/>
	<meta name="Author" content="jd.com"/>
	<meta name="Copyright" content="All Rights Reserved."/>
	<link rel="shortcut icon" type="image/x-icon" href="longIt.ico"/>
	<!-- regex.js是正则表达式的一系列判断 -->
	<script type=text/javascript src="${ctx}/js/regex.js"></script>
	<script type="text/javascript">
	   
	
	    jQuery(function(){
		    //改变输入框的高度    style="height:30px" 
		    jQuery("input").css("height","30px");
   
	    	
	    })
	
	
	
		var changFn = function(){
             jQuery("#JD_Verification1").attr("src","${ctx}/verify.action?date="+Math.random());
			//
	    	
		};
		
		
		var $ = function(id){
			return document.getElementById(id);
		};
		var formOnfocus = function(id){
			$(id + "_error").innerHTML = "";
			$(id + "_ok").innerHTML = "";
			switch (id){
				case "loginName":
					$("loginName_message").innerHTML = "请输入合法的邮箱地址";
					break;
				case "passWord":
					$("passWord_message").innerHTML = "可输入6-16位字符，可由英文、数字组成";
					break;
				case "okPassWord":
					$("okPassWord_message").innerHTML = "请输入确定密码";
					break;
				case "useName":
					$("userName_message").innerHTML = "请输入真实的用户姓名";
					break;
				case "address":
					$("address_message").innerHTML = "请输入详细的地址";
					break;
				case "phone":
					$("phone_message").innerHTML = "请输入手机号码";
					break;
				case "authcode":
					$("authcode_message").innerHTML = "请输入验证码";
					break;
			}
		};
		//所有控件输入完成,失去焦点的时候进行判断
		var formOnblur = function(id){
			var flag = false;
			$(id + "_message").innerHTML = "";
			var img = "<img alt='ok' src='images/pic_login_ok.gif'/>";
			var fieldVaue = $(id).value;
			if(id == "loginName"){
				if(fieldVaue == null || fieldVaue == "" || fieldVaue.length == 0){
					$("loginName_error").innerHTML = "请输入邮箱地址";
				}
				//检验是否是合法的邮箱地址
				if(checkEmail(fieldVaue)){
	    			$('loginName_ok').innerHTML = img;
	    			flag = true;
				}else{
					$('loginName_error').innerHTML="您输入的邮箱地址不合法";
				}
				
				//校验登录是否存在
				if(flag){
					jQuery.ajax({
						type:"post",
						url:"${ctx}/ajaxValidUser.action",
						data:"loginName="+fieldVaue,//  {"loginName":fieldVaue}  通过request.getParament 
						dataType:"json",//后台响应的数据类型   text   json   
						success:function(msg){
                              if(msg.key){
                            	  //清空用户输入的值
                            	  jQuery("#"+id).val("");
                            	  jQuery('#loginName_ok').html("");
                            	  jQuery('#loginName_error').html(msg.key);
                              }
						},error:function(){
							alert("数据加载失败");
						}
						
					})
				}
				
				
				
			}else if(id == "passWord"){
				if (fieldVaue == null || fieldVaue == "" || fieldVaue.length == 0) {
					$("passWord_error").innerHTML = "请输入6-16位字符，可由英文、数字组成";
				}
				if(checkPassword(fieldVaue)){
	    			$('passWord_ok').innerHTML= img;
	    			flag = true;
				}else{
					$('passWord_error').innerHTML="您输入的密码不合法";
				}
			}else if(id == "okPassWord"){
				if ($("passWord").value == null || $("passWord").value == "" || fieldVaue == ""
						|| fieldVaue == null || $("passWord").value != fieldVaue){
					$("okPassWord_error").innerHTML = "两次输入密码不一致";
				}else{
					$('okPassWord_ok').innerHTML= img;
					flag = true;
				}
			}else if (id == "userName"){
				if (fieldVaue == null || fieldVaue == "" || fieldVaue.length == 0){
					$("userName_error").innerHTML = "请输入真实姓名";
				}else{
					$("userName_ok").innerHTML = "<img alt='ok' src='images/pic_login_ok.gif'>";
					flag = true;
				}
			}else if (id == "address"){
				if (fieldVaue == null || fieldVaue == "" || fieldVaue.length == 0){
					$("address_error").innerHTML = "请输入联系地址";
				}else{
					$("address_ok").innerHTML = img;
					flag = true;
				}
			}else if (id == "phone"){
				if (fieldVaue == null || fieldVaue == "" || fieldVaue.length == 0){
					$("phone_error").innerHTML = "请输入真实姓名";
				}else{
					$("phone_ok").innerHTML = img;
					flag = true;
				}
			}else if(id == "authcode"){
				if(fieldVaue == null || fieldVaue == "" || fieldVaue.length == 0){
					$("authcode_error").innerHTML = "您输入的验证码不符法";
				}else{
					flag = true;
				}
			}
			return flag;
		};
		// 提交注册信息时，验证表单字段函数.
		var onRegister = function(){
			//ids:所有输入框的id值
			var ids = ["loginName","passWord","okPassWord","userName","address","phone","authcode"];
			for (var i = 0; i < ids.length; i++){
				//flag:false(验证不通过)
				var flag = formOnblur(ids[i]);
				if (!flag){
					return false;
				}
			}
			
			//每一个字段都能校验通过则提交表单
			jQuery("#registerform").submit();
		};
	</script>
</head>
<body>
	<!-- header部分 -->
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
	<!-- register bar -->
	<div class="w" id="regist">
		<div class="mt">
		    <h2><strong>注册新用户</strong></h2>
		    <span style="text-align: right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
			<span>我已经注册，现在就&nbsp;<a href="login.action">登录</a></span>
		</div>
		<div class="mc">
			<ul class="tab">
			    <li class="curr">个人用户信息</li>
			</ul>
			<!-- form -->
			<form id="registerform" method="post" name="registerform" action="${ctx}/register.action">
			  
			    <div class="form">
			    	<center style="color:red;"></center>
			    	<!-- loginName -->
			        <div class="item">
			            <span class="label"><b class="ftx04">*</b>登录名：</span>
			            <div class="fl">
			                <input type="text" id="loginName" name="loginName" 
			                class="text" 
							onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);">
							<span id="loginName_ok"></span>
			                <div id="loginName_message" style="clear:both;"></div>
							<label id="loginName_error" class="null"></label>
			            </div>
			        </div>
					<!-- loginName end -->
					<!-- passWord  -->
			        <div class="item">
			            <span class="label"><b class="ftx04">*</b>设置密码：</span>
			            <div class="fl">
			                <input type="password" id="passWord" name="passWord" 
							class="text"
							onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);">
							 <span id="passWord_ok"></span>
			                 <div id="passWord_message" style="clear:both;"></div>
							 <label id="passWord_error" class="null"></label>
			            </div>
			        </div>
			        <div class="item">
			            <span class="label"><b class="ftx04">*</b>确认密码：</span>
			            <div class="fl">
			                <input type="password" id="okPassWord" name="okPassWord" class="text" 
							onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);">
							<span id="okPassWord_ok"></span>
			                <div id="okPassWord_message" style="clear:both;"></div>
			                <label id="okPassWord_error" class="null"></label>
			            </div>
			        </div>
					<!-- passWord end -->
					<!-- name  -->
					<div class="item">
			            <span class="label"><b class="ftx04">*</b>真实姓名：</span>
			            <div class="fl">
			                <input type="text" id="userName" name="name" 
			                class="text" 
							onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);">
							<span id="userName_ok"></span>
			                <div id="userName_message" style="clear:both;"></div>
							<label id="userName_error" class="null"></label>
			            </div>
			        </div>
					<!-- name end -->
					<!-- sex  -->
					<div class="item">
			            <span class="label"><b class="ftx04">*</b>性&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
			            <div class="fl">
			            	<label><input type="radio" value="1" name="sex" checked="checked"/>男</label>
			                <label><input type="radio" value="2" name="sex"/>女</label>
			            </div>
			        </div>
					<!-- sex end -->
					<!-- address -->
					<div class="item">
			            <span class="label"><b class="ftx04">*</b>详细地址：</span>
			            <div class="fl">
			                <input type="text" id="address" name="address" 
			                class="text"
							onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);">
							<span id="address_ok"></span>
			                <div id="address_message" style="clear:both;"></div>
							<label id="address_error" class="null"></label>
			            </div>
			        </div>
					<!-- address end -->
					<!-- phone -->
					<div class="item">
			            <span class="label"><b class="ftx04">*</b>联系电话：</span>
			            <div class="fl">
			                <input type="text" id="phone" name="phone" 
			                class="text" 
							onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);"/>
							<span id="phone_ok"></span>
			                <div id="phone_message" style="clear:both;"></div>
							<label id="phone_error" class="null"></label>
			            </div>
			        </div>
					<!-- phone end -->
					<!-- authcode -->
			        <div class="item">
			            <span class="label"><b class="ftx04">*</b>验证码：</span>
			            <div class="fl">
			                <input type="text" id="authcode" style="ime-mode:disabled" name="authcode" 
							class="text text-1"  maxlength="4" 
							onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);"/>
			                <label class="img">
			                    <img style="cursor:pointer;width:70px;height:25px;" title="验证码" onclick="changFn();" 
								 id="JD_Verification1" src="${ctx}/verify.action"> </label>
			                <label class="ftx23" style="height:26px;">&nbsp;看不清？
							<a href="javascript:changFn()">换一张</a></label>
			                <span id="authcode_ok"></span>
			                <div id="authcode_message" style="clear:both;"></div>
			                <label id="authcode_error" class="null"></label>
			            </div>
			        </div>
					<!-- authcode end -->
			        <div class="item">
			            <span class="label">&nbsp;</span>
						<!-- a href 调用js函数需要加javascript:function();  -->
			            <a href="javascript:void(0);" onclick="onRegister();"><img src="${ctx}/images/register.jpg"></a>
			        </div>
			    </div>
			</form>
			<!--form end-->
			<div id="protocol-con">
			    <h4>网站用户注册协议</h4>
			    <h5>第1条 本站服务条款的确认和接纳</h5>
			    ******************************************
			    <h5>第2条 本站服务</h5> 
			    ******************************************
			    <h5>第3条 用户信息</h5>
                 ******************************************			   
                <h5>第4条 配送</h5>
			    ******************************************
			</div>
		</div>
	</div>
</body>
</html>