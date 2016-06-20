window.onload = function() {
	adm_id = location.search.match(/\?adm_id=(\d+)/);
	if (adm_id == null) {
		location.href = "../../superadmin/adm_manage.jsp";
	}
}
function admin_update() {
	if (adm_id != null) {
		// 获取基本数据
		String
		adm_username = document.getElementById("username").value;
		String
		adm_pwd = document.getElementById("password").value;
		String
		academy_id = academy.options[academy.selectedIndex].value;

		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("POST", '../../servlet/superAdmin', true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttp.send('adm_id=' + adm_id[1] + '&adm_username=' + adm_username
				+ '&adm_pwd=' + adm_pwd + '&academy_id=' + academy_id
				+ '&action=1');
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var text = xmlHttp.responseText;
				alert(text);
				if (text == "修改成功！") {
					location.href = "../../superadmin/adm_manage.jsp";
				}
			}
		}
	} else {
		location.href = "../../superadmin/adm_manage.jsp";
	}
}