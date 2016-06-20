<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    if(checkLogin.checkL(request, response)==0){
    	response.sendRedirect("../login.jsp");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/user/changePsw.js"></script>
<title>修改密码页面</title>
</head>
<body>
	<div class="box">
		<div>
			<label>请输入原始密码：</label>
			<input type="password" id="oldPsw">
		</div>
		<div>
			<label>请输入新密码：</label>
			<input type="password" id="newPsw">
		</div>
		<div >
			<label>再次输入新密码：</label>
			<input type="password" id="ConfirmPwd">
		</div>
		<div>
			<input type="button" id="submit" onclick="submit()" value="提交">
		</div>
	</div>
</body>
</html>