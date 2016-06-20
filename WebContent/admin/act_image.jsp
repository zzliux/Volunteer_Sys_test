<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 if(checkLogin.checkL(request, response)!=2){
		response.sendRedirect("../login.jsp");
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/activity/admin_deleteimage.js"></script>
<title>图片管理页面</title>
</head>
<body>
	<div id="box">
		<div id="title">活动标题：</div>
		<div id="image">
			本活动图片：
			
		</div>
	</div>
</body>
</html>