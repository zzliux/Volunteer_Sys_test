<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入页面</title>
<script src="http://cdn.bootcss.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript" src="./js/user/login.js"></script>
<script type="text/javascript">
function reloadCode(){
	var time = new Date().getTime();
		document.getElementById("imagecode").src ="./randomcode?d=" + time;
}
</script>
</head>
<body>
	<div class="box">
		<div class="user">
			<select id="User">
				<option value="1">管理员</option>
				<option value="2">学生</option>
			</select>
		</div>
		<div class="uname">
			<label>用户名：</label> <input type="text" id="UserName"
				placeholder="UserName">
		</div>
		
		<div class="psw">
			<label>密码：</label> <input type="password" id="PassWord"
				placeholder="PassWord">
		</div>
		<div class="check">
			<label>验证码：</label> <input type="text" id="CheckCode"> <img
				id="imagecode" alt="验证码" src="./randomcode"> <a
				href="javascript:reloadCode();">看不清楚</a><br>
		</div>
		<div class="loginbtn">
			<input type="button" value="登入" onclick="submit()">&nbsp;&nbsp; <a
				href="register.jsp">注册</a>
		</div>
		<!-- 注册提示 
		<div id="meg" display:none>
			test
		</div>
		-->
	</div>
</body>
</html>