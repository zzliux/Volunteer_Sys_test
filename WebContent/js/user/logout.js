function logout() {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", '/Volunteer_Sys_test/servlet/logout', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			var text = xmlHttp.responseText;
			alert(text);
			location.reload();
		}
	};
}
