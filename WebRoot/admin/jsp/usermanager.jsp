<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	
		<form action="${ctx}/userManager.action" method="post">		
				<table width="450" align="center" style="font-size:14px;">
					<tr>
						<td><font color="red" id="mess">${message}</font></td>
					</tr>
					<tr align="center">
						<td>登录名：</td>
						<td><input type="text" id="loginName" name="loginName"></td>
					</tr>
					<tr align="center">
						<td>登录密码：</td>
						<td><input type="password" id="passWord" name="passWord"></td>
					</tr>
					<tr align="center">
						<td>性&nbsp;别：</td>
						<td>
							<input type="radio" value="1" name="sex" checked="checked"/>男
							<input type="radio" value="2" name="sex" />女
						</td>
					</tr>
					<tr align="center">
						<td>联系电话：</td>
						<td><input type="text" id="phone" name="phone"/></td>
					</tr>
					<tr align="center">
						<td>详细地址：</td>
						<td><input type="text" id="address" name="address"/></td>
					</tr>
					<tr align="center">
						<td></td>
						<td><button type="submit"  id="btn_submit">修改信息</button></td>
					</tr>
				</table>
			</form>
		 
</body>
</html>