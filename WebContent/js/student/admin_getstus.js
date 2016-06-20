window.onload = function() {
	document.getElementById("msg").innerHTML = "加载中...啦啦啦！";
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", '../servlet/admin_Stus_show', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if (text != "empty") {
				var json = JSON.parse(text);
				document.getElementById("msg").innerHTML = "";
				autocreate(json);
				// console.log(json);
			}
		}
	}
}

function autocreate(json) {
	// 创建table表格
	var table = document.getElementById("tab");
	for (var i = 0; i < json.length; i++) {
		var tr = document.createElement("tr");
		// 序号
		var id = document.createElement("td");
		id.innerHTML = i + 1;

		// 姓名
		var stu_name = document.createElement("td");
		stu_name.innerHTML = json[i].stu_name;

		// 学号
		var stu_account = document.createElement("td");
		stu_account.innerHTML = json[i].stu_account;

		// 性别
		var stu_sex = document.createElement("td");
		stu_sex.innerHTML = json[i].stu_sex;

		// 电话
		var stu_phone = document.createElement("td");
		stu_phone.innerHTML = json[i].stu_phone;

		// QQ
		var stu_qq = document.createElement("td");
		stu_qq.innerHTML = json[i].stu_qq;

		// 邮箱
		var stu_email = document.createElement("td");
		stu_email.innerHTML = json[i].stu_email;

		// 学院名称
		var aca_name = document.createElement("td");
		aca_name.innerHTML = JSON.parse(json[i].academy_info).aca_name;

		// 查看信息
//		var stu_mes = document.createElement("td");
//		stu_mes.innerHTML = "<a href=\"\">查看具体信息</a>";

		// 删除
		var stu_delete = document.createElement("td");
		stu_delete.innerHTML = "<input type=\"button\" value=\"删除\" onclick=\"stu_delete("
				+ json[i].stu_id + ")\">";

		// 修改
		var stu_update = document.createElement("td");
		stu_update.innerHTML = "<a href=\"./stuupdate?stu_id=" + json[i].stu_id
				+ "\">修改</a>"

		//查看学生参加的活动
		var stu_act = document.createElement("td");
		stu_act.innerHTML = "<a href=\"./stu_act_manage.jsp?account="+json[i].stu_account+"\">查看该学生所参加的活动</a>";
				
				
				
		tr.appendChild(id);
		tr.appendChild(stu_name);
		tr.appendChild(stu_account);
		tr.appendChild(stu_sex);
		tr.appendChild(stu_phone);
		tr.appendChild(stu_qq);
		tr.appendChild(stu_email);
		tr.appendChild(aca_name);
//		tr.appendChild(stu_mes);
		tr.appendChild(stu_delete);
		tr.appendChild(stu_update);
		tr.appendChild(stu_act);

		table.appendChild(tr);
	}
}
