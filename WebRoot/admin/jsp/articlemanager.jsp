<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<script>
$(function(){
	
	$("#addForm").submit(function(){
		if(!checkEmpty("name","分类名称"))
			return false;
		if(!checkEmpty("categoryPic","分类图片"))
			return false;
		return true;
	});
});

</script>


	<form action="${ctx}/articleManager.action" method="post">		
				<table width="450" align="center" style="font-size:14px;">
					<tr>
						<td><font color="red" id="mess">${message}</font></td>
					</tr>
					<tr align="center">
						<td>商品ID：</td>
						<td><input type="text" id="articleId" name="articleId"></td>
					</tr>
					<tr align="center">
						<td>商品标题：</td>
						<td><input type="text" id="articleTitle" name="articleTitle"></td>
					</tr>
					<tr align="center">
						<td>商品价格：</td>
						<td>
							<input type="text" id="price" name="price"/>
						</td>
					</tr>
					<tr align="center">
						<td>供应商：</td>
						<td><input type="text" id="supplier" name="supplier"/></td>
					</tr>
					<tr align="center">
						<td>出场地：</td>
						<td><input type="text" id="locality" name="locality"/></td>
					</tr>
					<tr align="center">
						<td>商品库存：</td>
						<td><input type="text" id="storage" name="storage"/></td>
					</tr>
					<tr align="center">
						<td>商品折扣：</td>
						<td><input type="text" id="discount" name="discount"/></td>
					</tr>
					<tr align="center">
						<td></td>
						<td><button type="submit"  id="btn_submit">修改商品</button></td>
					</tr>
				</table>
			</form>
</body>
</html>