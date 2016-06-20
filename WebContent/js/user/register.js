//注册表单提交
function submit() {
	document.getElementById("meg").innerHTML = "注册中。。。";
	
	username = document.getElementById("UserName").value;
	academy = Academy.options[Academy.selectedIndex].value;
	account = document.getElementById("Account").value;
	password = document.getElementById("PassWord").value;
	conpsw = document.getElementById("Confirm_Password").value;
	gender_val = $('.ugender input[name="Gender"]:checked').val();
	tel = document.getElementById("Tel").value;
	qq = document.getElementById("QQ").value;
	ema = document.getElementById("Email").value;
	checkcode = document.getElementById("CheckCode").value;
	/*
	document.write('UserName=' + username + '&Account=' + account
			+ '&PassWord=' + password + '&Confirm_Password=' + conpsw
			+ "&Gender=" + gender + '&Tel=' + tel + '&QQ=' + qq + '&Email='
			+ ema + '&Academy=' + academy);
	*/
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", './servlet/register', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send('UserName=' + username + '&Account=' + account + '&PassWord='
			+ password + '&Confirm_Password=' + conpsw + "&Gender=" + gender_val
			+ '&Tel=' + tel + '&QQ=' + qq + '&Email=' + ema + '&Academy='
			+ academy + "&CheckCode=" + checkcode);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			document.getElementById("meg").innerHTML = text;
			reloadCode();
		}
	}
}