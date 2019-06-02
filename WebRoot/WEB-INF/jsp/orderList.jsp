<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>购物商城-订单列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="longIt.ico" rel="shortcut icon" type="image/x-icon"/>
		
		<script type="text/javascript">
	
		    //第一个参数：被选中的checkbox    第二个参数：行号
			//var viewitem = function(obj, id){
				//获取所有的tr
				//var trs = document.getElementsByTagName("tr");
				//遍历所有的tr
				/* for (var i = 0; i < trs.length; i++){
					//假设tr的id值包括  title_或者包括 book_,当前checkbox是选中的状态则将对应的订单明细显示，否则影藏
					if (trs[i].id.indexOf("title_" + id) == 0 
							|| trs[i].id.indexOf("book_" + id) == 0){
						trs[i].style.display = (obj.checked) ? "" : "none";
					}
				}
			};  */
			
			var viewitem = function(obj, id){
				var trs = $("tr");
				
				//遍历所有的tr
				$.each(trs,function(){
					if (this.id.indexOf("title_" + id) == 0 || this.id.indexOf("book_" + id) == 0){
						this.style.display = (obj.checked) ? "" : "none";
					}
				});
				
			}
			
		
		</script>
	</head>
<body>
	<!-- header部分 -->
	<div id="shortcut">
		<script type="text/javascript">header("${session_user.name}");</script>
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
	<div class="Wrap_cart">
		<div class="List_cart marginb10">
			<h2><strong>订单信息查询</strong></h2>
			<div class="cart_table">
				<div id="part_cart">
					<div class="o_show">
					   <c:choose>
					      <c:when test="${empty orderList}">
					         <%-- 如果没有订单信息 --%>
							<h1 style="font-weight:bold;font-size:12px;">&nbsp;&nbsp;<font color="red">${session_user.name}</font>：您好；您暂时没有订单！</h1>
							<div class="middle">
								<table class="Table" cellpadding="0" cellspacing="0" width="100%">
									<tr class="align_Center Thead">
										<td align="center">订单编号</td>
										<td width="26%" align="center">下单时间</td>
										<td width="20%" align="center">发货时间</td>
										<td width="16%" align="center">订单状态</td>
										<td width="20%" align="center">订单总额</td>
									</tr>
								</table>
								<br/>
									<center>暂时无信息！</center>
								<br/>
							</div>
					      </c:when>
					      <c:otherwise>
					         <%-- 如果存在订单信息时 --%>
							<h1 style="font-weight:bold;font-size:12px;"><font color="red">&nbsp;&nbsp;${session_user.name}</font>：您好；您当前共有[${fn:length(orderList)}]个订单：(点击订单可显示该订单下的购物明细)</h1>
							<div class="middle">
								<table class="Table" cellpadding="0" cellspacing="0" width="100%">
									<c:forEach items="${orderList}" var="order" varStatus="stat">
										<tr class="align_Center Thead">
											<td align="center" width="30%">订单编号</td>
											<td width="20%" align="center">下单时间</td>
											<td width="20%" align="center">发货时间</td>
											<td width="16%" align="center">订单状态</td>
											<td width="20%" align="center">订单总额</td>
										</tr>
										<tr class="align_Center" height="25">
											<td align="center">
												 ${stat.index+1}、<input type="checkbox" onclick="viewitem(this, ${stat.index});"/>${order.orderCode}
											</td>
											<td width="26%" align="center"> <fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd"/> </td>
											<td width="20%" align="center"> <fmt:formatDate value="${order.sendDate}" pattern="yyyy-MM-dd"/></td>
											<td width="16%" align="center">
												<c:if test="${order.status == 0}"><font color="red">未发货</font></c:if>
												<c:if test="${order.status == 1}">已发货</c:if>
											</td>
											<td width="20%" align="center"><fmt:formatNumber value="${order.amount}" pattern="0.00"></fmt:formatNumber> </td>
									 	</tr>
										<tr style="display:none;background-color:#CCCCCC;border-bottom-style: solid;" id="title_${stat.index}">
											<td align="center">物品图片</td>
											<td  align="center">标题</td>
											<td align="center">供应商</td>
											<td align="center">价格</td>
											<td  align="center">购买数量</td>
										</tr>
										<c:forEach items="${order.items}" var="orderItem" varStatus="st">
											<tr style="display:none;" id="book_${stat.index}_${st.index}" >
												<td  align="center">
													<a href="item.action?id=${orderItem.article.id}" title="${orderItem.article.title}">
	                  								<img width="60px" height="60px" alt="${orderItem.article.title}" src="images/article/${orderItem.article.image}" style="display: inline;"></a>
	                  							</td>
												<td  align="center">
													<a href="item.action?id=${orderItem.article.id}" title="${orderItem.article.title}">${orderItem.article.title}</a>
												</td>
												<td  align="center">${orderItem.article.supplier}</td>
												<td  align="center">${orderItem.article.price}</td>
												<td  align="center">${orderItem.orderNum}</td>
										 	</tr>
	                                	</c:forEach>
	                                </c:forEach>
								</table>
							</div>
					      </c:otherwise>
					   </c:choose>
						
						
						
							<div class="footer"></div>
					
					</div>
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