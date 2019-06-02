<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	
		<form action="${ctx}/orderManager.action" method="post">		
				<table width="450" align="center" style="font-size:14px;">
					<tr>
						<td><font color="red" id="mess">${message}</font></td>
					</tr>
					<tr align="center">
						<td>订单ID：</td>
						<td><input type="text" id="orderID" name="orderID"></td>
					</tr>
					<tr align="center">
						<td>用户ID：</td>
						<td><input type="text" id="userID" name="userID"></td>
					</tr>
					<tr align="center">
						<td>订单状态：</td>
						<td><input type="text" id="status" name="status" value="未发货：0 已发货 ：1"></td>
					</tr>
					<tr align="center">
						<td></td>
						<td><button type="submit"  id="btn_submit">修改订单</button></td>
					</tr>
				</table>
			</form>
		 
</body>
</html>