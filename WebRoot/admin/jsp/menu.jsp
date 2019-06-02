<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>购物商城-导航</title>
	<link rel="stylesheet" href="../css/commons.css" type="text/css" />
	<style type="text/css">
		*{margin:0;padding:0;}
		body{background-color:#C0DFFD;font-size:14px;text-align:center;}
	</style>
</head>
<body>
	<div class="admin_memu">
		<ul id="nav">
        	<li><a href="javascript:void(0);" style="text-align:center;" onclick="c" class="parent" id="1"><strong><font color="blue">商城后台管理</font></strong></a>
            	<ul id="opt_1" class="child_area">
            		<!-- HREF指链接到的目标,target指在哪里显示链接的页面，fraMainFrame在center.jsp的框架里面定义了 -->
					<li class="last"><a href="${ctx}/admin/jsp/usermanager.jsp" target="fraMainFrame">用户管理</a></li>
					<li class="last"><a href="${ctx}/admin/jsp/articlemanager.jsp" target="fraMainFrame">物品管理</a></li>
					<li class="last"><a href="${ctx}/admin/jsp/ordermanager.jsp" target="fraMainFrame">订单管理</a></li>
					<li class="last"><a href="${ctx}/loginout.action" target="_top">安全退出</a></li>
                </ul>
            </li>
        </ul>
	</div>
</body>
</html>