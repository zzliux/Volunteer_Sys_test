<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script src="http://cdn.bootcss.com/jquery/2.0.0/jquery.js"></script>
<script src="./js/user/register.js" type="text/javascript"></script>
<script type="text/javascript">
	function reloadCode() {
		var time = new Date().getTime();
		document.getElementById("imagecode").src = "./randomcode?d=" + time;
	}
</script>
</head>
<body>
	<div class="box">
		<!-- 账号信息 -->
		<div class="Account_information">
			<font>账号信息：</font>
			<div class="act">
				<label>学号</label> <input type="text" id="Account"
					placeholder="Account">
			</div>
			<div class="psw">
				<label>密码</label> <input type="password" id="PassWord"
					placeholder="PassWord">
			</div>

			<div class="conpsw">
				<label>确认密码</label> <input type="password" id="Confirm_Password"
					placeholder="Confirm_Password">
			</div>

		</div>
		<!-- 基本信息 -->
		<div class="Basic_information">
			<font>基本信息：</font>
			<div class="uname">
				<label>姓名</label> <input type="text" id="UserName"
					placeholder="UserName">
			</div>

			<div class="academy">
				<label>学院</label> <select id="Academy">
					<option value="1">计算机与信息工程学院</option>
					<option value="2">外国语学院学院</option>
					<option value="3">会计学院</option>
					<option value="4">艺社</option>
				</select>
			</div>

			<div class="ugender">
				<label>性别</label> 男<input type="radio" name="Gender" value="男">
				女<input type="radio" name="Gender" value="女">
			</div>

			<div class="utel">
				<label>电话</label> <input type="text" id="Tel" placeholder="Tel">
			</div>

			<div class="uqq">
				<label>QQ</label> <input type="text" id="QQ" placeholder="QQ">
			</div>

			<div class="ema">
				<label>电子邮箱</label> <input type="text" id="Email"
					placeholder="Email">
			</div>
		</div>

		<div class="check">
			<label>验证码：</label> <input type="text" id="CheckCode"> <img
				id="imagecode" alt="验证码" src="./randomcode"> <a
				href="javascript:reloadCode();">看不清楚</a><br>
		</div>
		<!-- 注册提示 -->
		<div id="meg">test</div>
		<div class="reg">
			<input type="submit" value="点击注册" onclick="submit()">
		</div>
	</div>
</body>
</html>