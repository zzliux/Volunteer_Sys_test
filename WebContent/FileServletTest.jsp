<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if(checkLogin.checkL(request, response)!=2){
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="./js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript">
function submit() {
	$('#uploadLog').html("开始上传中。。。");
	//setTimeout(function(){},1000)
	$("#frameFile").load(function(){
		$('#formFile').submit();
		//alert("test");
		$('#uploadLog').html(  $(window.frames['frameFile'].document.body).find("pre").html());
		$("#fileUp").val('');
	});
}

</script>
<title>图片上传测试</title>
</head>
<body>
	<h1>管理员才能上传图片(一个小测试)</h1>
	 <div id='uploadLog'></div>
	<form id="formFile" name="formFile" method="post" action="servlet/fileServlet" enctype="multipart/form-data" target='frameFile'>
		请选择图片：<input name="fileUp" id="fileUp" type="file" size="20">
		<input type="hidden" value="2" name="id">
		<input type="button" value="提交" onclick="submit()">
	</form>
	<iframe id='frameFile' name='frameFile'></iframe>
</body>
</html>