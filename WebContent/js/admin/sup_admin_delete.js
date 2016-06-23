function admin_delete(adm_id){
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", '../servlet/superAdmin', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send('adm_id='+adm_id+'&action=0');
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
			var text = xmlHttp.responseText;
			alert(text);
			document.location.reload();
		}
	};
}
