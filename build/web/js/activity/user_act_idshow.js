window.onload = function() {
	id = location.search.match(/\?id=(\d+)/);
	if (id != null) {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("POST", '../servlet/basicAct_show', true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttp.send("act_id=" + id[1]);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var text = xmlHttp.responseText;
				//console.log(text);// 测试
				if(text!="error"){
					autocreate(JSON.parse(text));
				}else{
					location.href="../index.jsp";
				}
			}
		}
	} else {
		location.href = "../index.jsp";
	}
}

function autocreate(json) {
	var box = document.getElementById("box");

	//图片
	var imageUrls = json.act_imgurl.split(',');
	for(var i=0;i<imageUrls.length-1;i++){
		var image = document.createElement("img");
		image.src = "http://xxmodd.com:8080/Volunteer_Sys_test/image/"+imageUrls[i];
		image.alt = "哎呀，显示不出来辣！";
		document.getElementById("image").appendChild(image);
	}
	
	// 标题
	var act_title = document.createElement("label");
	act_title.innerHTML = json.act_title;

	// 开始时间
	var act_startTime = document.createElement("label");
	act_startTime.innerHTML = json.act_startTime;
	// 结束时间
	var act_endTime = document.createElement("label");
	act_endTime.innerHTML = json.act_endTime;

	// 活动状态
	var act_status = document.createElement("label");
	var statu = json.act_status;
	if (statu == "1") {
		act_status.innerHTML = "进行中";
	} else if (statu == "2") {
		act_status.innerHTML = "已结束";
	} else {
		act_status.innerHTML = "未开始";
	}

	// 学院名称
	var aca_name = document.createElement("label");
	aca_name.innerHTML = JSON.parse(json.academy_info).aca_name;
	// 学院负责人
	var aca_man = document.createElement("label");
	aca_man.innerHTML = JSON.parse(json.academy_info).aca_man;

	// 最多报名人数
	var act_enrollment = document.createElement("label");
	act_enrollment.innerHTML = json.act_enrollment;

	// 实际报名人数
	var act_actual_enrollment = document.createElement("label");
	act_actual_enrollment.innerHTML = json.act_actual_enrollment;

	// 活动内容
	var act_content = document.createElement("p");
	act_content.innerHTML = json.act_content;

	document.getElementById("title").appendChild(act_title);
	document.getElementById("sdate").appendChild(act_startTime);
	document.getElementById("edate").appendChild(act_endTime);
	document.getElementById("statu").appendChild(act_status);
	document.getElementById("name").appendChild(aca_name);
	document.getElementById("allenrollment").appendChild(act_enrollment);
	document.getElementById("enrollment").appendChild(act_actual_enrollment);
	document.getElementById("content").appendChild(act_content);
}