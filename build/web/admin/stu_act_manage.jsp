<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Activity"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.info_Query"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Students"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (checkLogin.checkL(request, response) != 2) {
		response.sendRedirect("../login.jsp");
	}else{
		String aut = null;
		Students stu = null;
		try{
			aut = request.getParameter("account");
			stu = info_Query.stuAutQuery(aut);
		

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/student/admin_stu_act_delete.js"></script>
<title>学生报名活动管理</title>
</head>
<body>
	<label>学生基本信息</label><br>
	<div class="info">
		<div id="name">姓名：<%=stu.getStu_name()%></div>
			
		<div id="account">学号：<%=stu.getStu_account()%></div>
			
		<div id="sex">性别：<%=stu.getStu_sex()%></div>
			
		<div id="phone">电话：<%=stu.getStu_phone()%></div>
			
		<div id="qq">QQ：<%=stu.getStu_qq()%></div>
			
		<div id="email">邮箱：<%=stu.getStu_email()%></div>
	</div><br>
	<label>该学生所参加的活动：</label><br>
	<table border="1">
		<tr>
			<td>序号</td>
			<td>活动标题</td>
			<td>活动状态</td>
			<td>操作</td>
		</tr>
	<%
		List<Activity> actList = stu.getActList();
		for (int i = 0; i < actList.size(); i++) {
			Activity act = actList.get(i);
			String act_status;
			int status = act.getAct_status();
			if(status==1){
				act_status = "进行中";
			}else if(status==2){
				act_status = "已结束";
			}else{
				act_status = "未开始";
			}
	%>
	<tr>
			<td><%=i+1%></td>
			<td><%=act.getAct_title() %></td>
			<td><%=act_status %></td>
			<td><input type="button" value="删除" onclick="stu_act_delete(<%=stu.getStu_id()%>,<%=act.getAct_id()%>)"></td>
	</tr>
<%
		}

		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("./stu_manage.jsp");
		}
	} 
%>
	</table>
</body>
</html>