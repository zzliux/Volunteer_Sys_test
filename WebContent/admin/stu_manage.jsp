<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (checkLogin.checkL(request, response) != 2) {
		response.sendRedirect("../login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理页面</title>
<script type="text/javascript" src="../js/student/admin_getstus.js"></script>
<script type="text/javascript" src="../js/student/admin_stu_delete.js"></script>

</head>
<body>
	<div class="box">
		<table id="tab" border="1">
			<tr>
				<td>序号：</td>
				<td>姓名：</td>
				<td>学号：</td>
				<td>性别：</td>
				<td>电话：</td>
				<td>QQ：</td>
				<td>邮箱：</td>
				<td>所属学院</td>
				<!--
				<td>查看具体信息</td>
				 -->
				<td>操作</td>
				<td>操作</td>
				<td>操作</td>
			</tr>
		</table>
		<label id="msg"></label>
	</div>
</body>
</html>