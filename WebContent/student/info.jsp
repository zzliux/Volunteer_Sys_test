<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Activity"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Academy"%>
<%@page import="JSON.JsonHelper"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Students"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Students stu = null;
	Academy aca = null;
	if (checkLogin.checkL(request, response) != 1) {
		response.sendRedirect("../login.jsp");
	} else {
		stu = (Students) request.getSession().getAttribute("stuinfo");
		aca = new Academy();
		JsonHelper.toJavaBean(aca, stu.getAcademy_info().toString());
		//out.print(stu.toString());
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生个人信息</title>
<!-- 
<script type="text/javascript" src="../js/user/user_getinfo.js"></script>
 -->
</head>
<body>
	<%
		if (stu != null) {
	%>
	<h1>个人信息</h1>
	<div class="info">
		<div id="name">姓名：<%=stu.getStu_name()%></div>
			
		<div id="account">学号：<%=stu.getStu_account()%></div>
			
		<div id="sex">性别：<%=stu.getStu_sex()%></div>
			
		<div id="phone">电话：<%=stu.getStu_phone()%></div>
			
		<div id="qq">QQ：<%=stu.getStu_qq()%></div>
			
		<div id="email">邮箱：<%=stu.getStu_email()%></div>
			
		<div id="acaname">学院：<%=aca.getAca_name()%></div>
		
		<div id="acaman">学院负责人：<%=aca.getAca_man()%></div>
		
		<div id="acaphone">学院负责人电话：<%=aca.getAca_phone()%></div>
	</div>
	<div>
		<a href="../changePassword">修改密码</a>
	</div>
	<div class="act">

		<label>您所参加的活动:</label><br>
		<!-- 已参加且正在进行的活动 -->
		<div id="on">
			<label>进行中:</label><br>
			<%
				//获取该学生所参加的活动
					int num = 0;
					List<Activity> actList = stu.getActList();
					for (int i = 0; i < actList.size(); i++) {
						Activity act = actList.get(i);
						if (act.getAct_status() != 2) {
			%>
			<a href="../activities?id=<%=act.getAct_id()%>"><%=act.getAct_title()%></a><br>
			<%
				num++;
						}
					}
					if (num == 0) {
						out.print("无");
					}
			%>
		</div>



		<!-- 已参加但已结束的活动 -->
		<div id="over">
			<label>已结束:</label><br>
			<%
				num = 0;
					for (int i = 0; i < actList.size(); i++) {
						Activity act = actList.get(i);
						if (act.getAct_status() == 2) {
			%>
			<a href="../activities?id=<%=act.getAct_id()%>"><%=act.getAct_title()%></a><br>
			<%
				num++;
						}
					}
					if (num == 0) {
						out.print("无");
					}
			%>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>