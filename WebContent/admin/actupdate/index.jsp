<%@page import="cn.edu.hnuc.volunteer_Sys.util.info_Query"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Activity"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	if(checkLogin.checkL(request, response)!=2){
		response.sendRedirect("../../login.jsp");
	}else{
		int act_id;
		Activity act = null;
		try{
			act_id = Integer.parseInt(request.getParameter("act_id"));
			act = info_Query.actQuery(act_id);
		

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../js/activity/admin_act_update.js"></script>
<title>修改活动页面</title>
</head>
<body>
	<table>
		<tr>
			<td><label>活动名称：</label></td>
			<td><input type="text" id="title" value="<%=act.getAct_title()%>"></td>
		</tr>
		<tr>
			<td><label>活动内容：</label></td>
			<td><textarea rows="5" cols="5" id="content" style="margin: 0px; height: 327px; width: 461px;"><%=act.getAct_content()%></textarea></td>
		</tr>
		<tr>
			<td><label>活动开始时间：</label></td>
			<td><input type="date" id="sdate"  value="<%=act.getAct_startTime()%>"/></td>
		</tr>
		<tr>
			<td><label>活动结束时间：</label></td>
			<td><input type="date" id="edate"  value="<%=act.getAct_endTime()%>"/></td>
		</tr>
		<tr>
			<td><label>设置报名人数： </label></td>
			<td><input type="text" id="enrollment" value="<%=act.getAct_enrollment()%>"></td>
		</tr>
		<tr>
			<td><input type="button" onclick="act_update()" value="提交"></td>
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