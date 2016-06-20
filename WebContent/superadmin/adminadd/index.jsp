<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (checkLogin.checkL(request, response) != 3) {
		response.sendRedirect("../../login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../js/admin/sup_admin_add.js"></script>
<title>添加管理员</title>
</head>
<body>
	<div>
		<h1>添加管理员页面</h1>
	</div>
	<div class="uname">
		<label>账号</label> <input type="text" id="username">
	</div>
	<div class="psw">
		<label>密码</label> <input type="password" id="password">
	</div>
	<div class="aca">
		<label>学院</label> <select id="academy">
			<option value="1">计算机与信息工程学院</option>
			<option value="2">外国语学院学院</option>
			<option value="3">会计学院</option>
			<option value="4">艺社</option>
		</select>
	</div>
	<div class="btn">
		<input type="button" value="添加" onclick="admin_add()">
	</div>

</body>
</html>