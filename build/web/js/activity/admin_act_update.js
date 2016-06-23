var act_id = null;
window.onload = function() {
	act_id = location.search.match(/\?act_id=(\d+)/);
	if(act_id === null){
		location.href = "../../admin/act_manage.jsp";
	}
};
function act_update() {
	if (act_id !== null) {
		// 获取基本数据
		var	act_title = document.getElementById("title").value;
		var	act_content = document.getElementById("content").value;
		var	act_startTime = document.getElementById("sdate").value;
		var	act_endTime = document.getElementById("edate").value;
		var	act_enrollment = document.getElementById("enrollment").value;

		var xmlHttp = new XMLHttpRequest();
		// var act_Id = document.getElementById("act_Id");
		xmlHttp.open("POST", '../../servlet/admin_Acts_update', true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttp.send('act_id='+act_id[1]+'&act_title=' + act_title + '&act_content=' + act_content
				+ '&act_startTime=' + act_startTime + '&act_endTime='
				+ act_endTime + '&act_enrollment=' + act_enrollment
				+ '&action=1');
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
				var text = xmlHttp.responseText;
				alert(text);
				if(text === "修改成功！"){
					location.href = "../../admin/act_manage.jsp";
				}
			}
		};
	}else{
		location.href = "../../admin/act_manage.jsp";
	}
}