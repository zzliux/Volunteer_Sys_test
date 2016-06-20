<%@page import="cn.edu.hnuc.volunteer_Sys.util.info_Query"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Students"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (checkLogin.checkL(request, response) != 2) {
		response.sendRedirect("../../login.jsp");
	}else{
		int stu_id;
		Students stu = null;
		try{
			stu_id = Integer.parseInt(request.getParameter("stu_id"));
			stu = info_Query.stuIdQuery(stu_id);
		

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改学生信息</title>
<script type="text/javascript"
	src="../../js/student/admin_stu_update.js"></script>
<script src="http://cdn.bootcss.com/jquery/2.0.0/jquery.js"></script>
</head>
<body>
	<table>
		<tr>
			<td><label>学号：</label></td>
			<td><input type="text" id="account" value="<%=stu.getStu_account()%>"></td>
		</tr>
		<tr>
			<td><label>密码：</label></td>
			<td><input type="password" id="psw"></td>
		</tr>
		<tr>
			<td><label>姓名：</label></td>
			<td><input type="text" id="sname" value="<%=stu.getStu_name()%>"></td>
		</tr>
		<tr>
			<td><label>性别：</label></td>
			<%
				if(stu.getStu_sex().equals("男")){
			%>
			<td class="ugender">男<input type="radio" name="gender" value="男" checked="checked">
				女<input type="radio" name="gender" value="女" >
			</td>
			<%
				}else{
			%>
			<td class="ugender">男<input type="radio" name="gender" value="男" checked="checked">
				女<input type="radio" name="gender" value="女" checked="checked">
			</td>
			<%	
				}
			%>
		</tr>
		<tr>
			<td><label>电话：</label></td>
			<td><input type="text" id="phone" value="<%=stu.getStu_phone()%>"></td>
		</tr>
		<tr>
			<td><label>QQ：</label></td>
			<td><input type="text" id="qq" value="<%=stu.getStu_qq()%>"></td>
		</tr>
		<tr>
			<td><label>邮箱：</label></td>
			<td><input type="text" id="email" value="<%=stu.getStu_email()%>"></td>
		</tr>
		<tr>
			<td></td>
			<td id="msg"></td>
		</tr>
		<tr>
			<td><input type="button" onclick="stu_update()" value="提交"></td>
		</tr>
	</table>
</body>
<%
		}catch(Exception e){
			response.sendRedirect("../../login.jsp");
		}
	}
%>
</html>