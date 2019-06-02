<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 引入分页标签 -->
<%@ taglib prefix="fk" uri="/pager-1609"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>购物商城-首页</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="longIt.ico" rel="shortcut icon" type="image/x-icon" />
		
		<!-- 使用jQuery-UI的样式来设置tab页 -->
		<link type="text/css" href="css/ui-lightness/jquery-ui-1.8.20.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-ui.js"></script>
		
		<script type="text/javascript">
			if (window.location != parent.window.location){
				parent.window.location = window.location;
			}
			window.onload = function(){
				/** tabs标签页 */
				$('#tabs').tabs();
				
				
				/** 获取所有的li为特定的li绑定事件 */
				var arrays = document.getElementsByTagName("li");
				for (var i = 0; i < arrays.length; i++){
					if (arrays[i].id != "" && arrays[i].id.indexOf('selbgc1') == 0){
						arrays[i].onmouseover = function(){
							this.className = "selbgc1";
						};
						arrays[i].onmouseout = function(){
							this.className = "";
						};
					}
				}

                //获取用户选择的物品类型   
                var typeCode = "${typeCode}";
                $("#typecode").val(typeCode);
  
			};
			
			
			//下拉框onchange事件
			function dataChange(obj){
				//获取用户输入的关键字
				var keyword = $("#keyword").val();
				window.location = "${ctx}/index.action?typeCode="+obj.value+"&keyword="+keyword;
				
			}
			
		</script>
	</head>
<body>
	<!-- header部分 -->
	<div id="shortcut">
		<script type="text/javascript">
		
		header("${session_user.name}");
		
		</script>
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
	<!-- middle part -->
	<div style="positon: relative; width: 960px;margin: 0px auto;">
		<!-- 左边物品类型列表 -->
		<div  id="booksort" style="float:left;width:210px;">
			<div class="mt" style="height:25px;font-size:14px;">
				<h2><strong>物品分类</strong></h2>
			</div>
			<div class="mc">
				<c:forEach items="${firstTypes}" var="firstType">
					 <div class="item"><h3><b>&gt;</b><a href="${ctx}/index.action?typeCode=${firstType.code}">·${firstType.name}</a></h3></div>
				</c:forEach>
				
				
			</div>
		</div>
		<!-- 右边对应物品列表 -->
		<div style="float:left;width:750px;text-align:center;">
			<div>
				<form action="${ctx}/index.action" method="post" name="search" >
			  		物品类型：
			  		<select name="typeCode" id="typecode" onchange="dataChange(this)">
				  		
				  		<c:forEach items="${seTypes}" var="articleType">
				  		      <option value="${articleType.code}">${articleType.name}</option>
				  		</c:forEach>
				  		
			  		</select>
				    <input name="keyword" type="text" id="keyword" value="${keyword}" size="50"/>
				    <button>搜索</button>
			  	</form>
			</div>
			<!-- 显示所有书籍 -->
			<div id="tabs" style="Width:750px;background-color:white;">
				<ul>
					<li><a href="tabs-1">${typeName}</a></li>
				</ul>
				<div class="sales-queue" id="tabs-1" style="background-color:white;margin-top:-25px;">
				    <ul class="goods-queue3">
						<c:forEach items="${articles}" var="article">
						     <li id="selbgc11">
							<dl class="item-des">      
							  <dt><a href="${ctx}/item.action?id=${article.id}" title="${article.title}" target="_self"><img src="${pageContext.request.contextPath}/images/article/${article.image}" width="132" height="96" /></a></dt>
							  <dd><s>¥: ${article.price}</s><strong>¥:<fmt:formatNumber value="${article.price*article.discount}" pattern="0.00"></fmt:formatNumber> </strong></dd>
							  <dd><h2><a href="${ctx}/item.action?id=${article.id}" title="${article.title}" target="_self">${article.title}</a></h2></dd>
							</dl>
						  </li>
						</c:forEach> 
					</ul>
					<!-- 分页标签 -->
					 <div class="pagebottom" id="pager" style="clear:both;">
				    <fk:page  submitUrl="index.action"  pageModel="${pageModel}"></fk:page></div>
				</div>
			</div>
		</div>
	</div>
	
	<!---- middle end----->
	
	<!--bottom part-->
	<div style="width: 1060px;margin: 0px auto;">
  		<img src="images/step.jpg"/>
  	</div>
</body> 
</html>
    