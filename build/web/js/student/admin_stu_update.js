window.onload = function() {
	stu_id = location.search.match(/\?stu_id=(\d+)/);
	if (stu_id == null) {
		location.href = "../../admin/stu_manage.jsp";
	}
}

function stu_update() {
	if (stu_id != null) {
		document.getElementById("msg").innerHTML="修改中...";
		// 获取基本数据
		String
		stu_account = document.getElementById("account").value;
		String
		stu_pwd = document.getElementById("psw").value;
		String
		stu_name = document.getElementById("sname").value;
		String
		stu_phone = document.getElementById("phone").value;
		String
		stu_qq = document.getElementById("qq").value;
		String
		stu_email = document.getElementById("email").value;
		stu_sex = $('.ugender input[name="gender"]:checked').val();

		var xmlHttp = new XMLHttpRequest();
		var action = 1;
		xmlHttp.open("POST", '../../servlet/admin_Stus_update', true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttp.send('stu_id='+stu_id[1]+'&stu_account=' + stu_account + '&stu_pwd=' + stu_pwd
				+ '&stu_name=' + stu_name + '&stu_phone=' + stu_phone
				+ '&stu_qq=' + stu_qq + '&stu_email=' + stu_email + '&stu_sex='
				+ stu_sex + '&action=' + action);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var text = xmlHttp.responseText;
				alert(text);
				location.href = "../../admin/stu_manage.jsp";
			}
		}
	} else {
		location.href = "../../admin/stu_manage.jsp";
	}
}