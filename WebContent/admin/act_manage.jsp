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
<script type="text/javascript" src="../js/activity/admin_getacts.js"></script>
<script type="text/javascript" src="../js/activity/admin_act_delete.js"></script>
<script src="../js/jquery-1.11.1.js"></script>
<title>活动管理页面</title>

<script type="text/javascript">
function submit() {
	$('#formFile').submit();
}
</script>

</head>
<body>
	<div class="box">
		<!-- 
		<div id="preview">
			<img id="previewImg" src="images/preview.jpg" width="80" height="80">
		</div>
		<div id="uploadmsg"></div>
		 -->
		 <iframe id='frameFile' name='frameFile' style="width: 150px;height: 30px;"></iframe>
		<div>
			<table id="tab"  border="1">
				<tr>
					<td>序号</td>
					<td>活动标题</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>活动状态</td>
					<td>学院名称</td>
					<td>学院负责人</td>
					<td>最大报名人数</td>
					<td>已报名人数</td>
					<td>操作</td>
					<td>操作</td>
					<td>操作</td>
					<td>上传图片</td>
					<td>操作</td>
				</tr>
			</table>
		</div>
		<div id="submitmsg"></div>
	</div>
</body>
</html>