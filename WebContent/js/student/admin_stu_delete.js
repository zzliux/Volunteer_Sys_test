function stu_delete(stu_id){
	var xmlHttp = new XMLHttpRequest();
	//var act_Id = document.getElementById("act_Id");
	xmlHttp.open("POST", '../servlet/admin_Stus_update', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send('stu_id='+stu_id+'&action=0');
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			alert(text);
			document.location.reload();
			
		}
	}
}