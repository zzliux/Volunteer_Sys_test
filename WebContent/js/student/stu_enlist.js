function enlist() {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", '../servlet/stu_enlist', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send("act_id="+id[1]);//这是个测试
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if (text == "error") {
				location.href = "../login.jsp";
			} else {
				//console.log(text);
				alert(text);
			}
		}
	}
}