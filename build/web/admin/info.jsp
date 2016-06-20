<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Admins"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	Admins admin = null;
  	if(checkLogin.checkL(request, response)!=2){
  		response.sendRedirect("../login.jsp");
  	}else{
  		admin = (Admins)request.getSession().getAttribute("adminfo");
  	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员个人信息</title>
</head>
<body>
	<%
		if (admin != null) {
	%>
	<h1>个人信息</h1>
	<div class="info">
		<div id="name">用户名：<%=admin.getAdm_username()%></div>
			
		<div id="acaname">学院：<%=admin.getAcademy_info().getAca_name()%></div>
		
		<div id="acaman">负责人：<%=admin.getAcademy_info().getAca_man()%></div>
		
		<div id="acaphone">学院负责人电话：<%=admin.getAcademy_info().getAca_phone()%></div>
	</div>
	<%
		}
	%>
	<div>
		<a href="../changePassword">修改密码</a>
	</div>
</body>
</html>