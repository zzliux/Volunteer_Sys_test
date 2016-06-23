function submit() {
	// 获取密码信息
	var oldPsw = document.getElementById("oldPsw").value;
	var newPsw = document.getElementById("newPsw").value;
	var ConfirmPwd = document.getElementById("ConfirmPwd").value;

	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", '../servlet/modify_UserPsw', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send('oldPsw=' + oldPsw + '&newPsw=' + newPsw + '&ConfirmPwd='
			+ ConfirmPwd);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			var text = xmlHttp.responseText;
			// document.getElementById("meg").innerHTML = "登入成功！";
			alert(text);
			if(text === "修改成功"){
				window.location.href="../index.jsp";
			}
		}
	};
}