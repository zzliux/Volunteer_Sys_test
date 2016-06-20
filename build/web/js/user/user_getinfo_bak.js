window.onload = function(){
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", '../servlet/user_info', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if(text!="empty"){
				json = JSON.parse(text);
				console.log(json);
				autocreate(json);
			}else{
				alert("请先登入！");
				window.location.href="../login.jsp";
			}
		}
	}
}
function autocreate(json) {
		// 姓名
		var stu_name = document.createElement("label");
		stu_name.innerHTML = json.stu_name;

		// 学号
		var stu_account = document.createElement("label");
		stu_account.innerHTML = json.stu_account;

		// 性别
		var stu_sex = document.createElement("label");
		stu_sex.innerHTML = json.stu_sex;

		// 电话
		var stu_phone = document.createElement("label");
		stu_phone.innerHTML = json.stu_phone;

		// QQ
		var stu_qq = document.createElement("label");
		stu_qq.innerHTML = json.stu_qq;

		// 邮箱
		var stu_email = document.createElement("label");
		stu_email.innerHTML = json.stu_email;

		// 学院名称
		var aca_name = document.createElement("label");
		aca_name.innerHTML = JSON.parse(json.academy_info).aca_name;
		
		//已参加的正在进行的活动
		
		//已完成的活动
		
		
		document.getElementById("name").appendChild(stu_name);
		document.getElementById("account").appendChild(stu_account);
		document.getElementById("sex").appendChild(stu_sex);
		document.getElementById("phone").appendChild(stu_phone);
		document.getElementById("qq").appendChild(stu_qq);
		document.getElementById("email").appendChild(stu_email);
		document.getElementById("aca").appendChild(aca_name);

}

