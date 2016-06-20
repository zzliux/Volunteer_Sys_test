<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>超级管理员登入页面</title>
<script type="text/javascript">
	function reloadCode() {
		var time = new Date().getTime();
		document.getElementById("imagecode").src = "./randomcode?d=" + time;
	}
</script>
</head>
<body>
	<h1>超级管理员登入页面</h1>
	<div class="form">
		<form action="adm_manage.jsp" method="post">
			<div class="uname">
				<label>用户名：</label> <input type="text" name="UserName"
					placeholder="UserName">
			</div>

			<div class="psw">
				<label>密码：</label> <input type="password" name="PassWord"
					placeholder="PassWord">
			</div>
			<div class="check">
				<label>验证码：</label> <input type="text" name="CheckCode"> <img
					id="imagecode" alt="验证码" src="../randomcode"> <a
					href="javascript:reloadCode();">看不清楚</a><br>
			</div>
			<div class="loginbtn">
				<input type="submit" value="登入">
			</div>
		</form>
	</div>
</body>
</html>