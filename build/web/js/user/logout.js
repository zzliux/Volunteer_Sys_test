function logout() {
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", './servlet/logout', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			alert(text);
			location.reload();
		}
	}
}