function admin_add() {
	// 获取基本数据
	var adm_username = document.getElementById("username").value;
	var adm_pwd = document.getElementById("password").value;
	var academy_id = academy.options[academy.selectedIndex].value;

	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", '../../servlet/superAdmin', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send('adm_username=' + adm_username + '&adm_pwd=' + adm_pwd
			+ '&academy_id=' + academy_id + '&action=2');
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			var text = xmlHttp.responseText;
			alert(text);
			if (text === "添加成功！") {
				location.href = "../../superadmin/adm_manage.jsp";
			}
		}
	}
}