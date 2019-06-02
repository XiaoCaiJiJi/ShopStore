<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 引入jstl相关标签 -->   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 给项目去别名 -->
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>

<!-- 引入jquery -->
<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.js"></script>

<!-- header.js输出头部信息 -->
<script type="text/javascript" src="${ctx}/js/header.js"></script>

<!-- main.css是购物商城主样式 -->
<link rel=stylesheet type="text/css" href="${ctx}/css/main.css"/>