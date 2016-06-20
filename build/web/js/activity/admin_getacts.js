window.onload = function() {
	document.getElementById("submitmsg").innerHTML = "加载中...啦啦啦！";
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.open("POST", '../servlet/admin_Acts_show', true);
	xmlHttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if (text != "empty") {
				var json = JSON.parse(text);
				document.getElementById("submitmsg").innerHTML = "";
				autocreate(json);
				// console.log(json);//测试
			}
		}
	}
}

function autocreate(json) {
	// 创建table表格
	var table = document.getElementById("tab");
	for (var i = 0; i < json.length; i++) {
		var tr = document.createElement("tr");
		// 序号
		var id = document.createElement("td");
		id.innerHTML = i + 1;
		// 标题
		var act_title = document.createElement("td");
		act_title.innerHTML = json[i].act_title;
		// 开始时间
		var act_startTime = document.createElement("td");
		act_startTime.innerHTML = json[i].act_startTime;
		// 结束时间
		var act_endTime = document.createElement("td");
		act_endTime.innerHTML = json[i].act_endTime;
		// 活动状态
		var act_status = document.createElement("td");
		var statu = json[i].act_status;
		if (statu == "1") {
			act_status.innerHTML = "进行中";
		} else if (statu == "2") {
			act_status.innerHTML = "已结束";
		} else {
			act_status.innerHTML = "未开始";
		}

		// 学院名称
		var aca_name = document.createElement("td");
		aca_name.innerHTML = JSON.parse(json[i].academy_info).aca_name;

		// 学院负责人
		var aca_man = document.createElement("td");
		aca_man.innerHTML = JSON.parse(json[i].academy_info).aca_man;

		// 最多报名人数
		var act_enrollment = document.createElement("td");
		act_enrollment.innerHTML = json[i].act_enrollment;

		// 实际报名人数
		var act_actual_enrollment = document.createElement("td");
		act_actual_enrollment.innerHTML = json[i].act_actual_enrollment;

		// 删除活动
		var act_delete = document.createElement("td");
		act_delete.innerHTML = "<input type=\"button\" value=\"删除\" onclick=\"act_delete("
				+ json[i].act_id + ")\">";

		// 增加活动
		var act_add = document.createElement("td");
		act_add.innerHTML = "<a href=\"./actadd\">添加活动</a>";

		// 修改活动
		var act_update = document.createElement("td");
		act_update.innerHTML = "<a href=\"./actupdate?act_id=" + json[i].act_id
				+ "\">修改</a>";
		
		// 上传图片
		var uploadImage = document.createElement("td");
		uploadImage.innerHTML = "<form id=\"formFile\" name=\"formFile\" method=\"post\" action=\"../servlet/fileUpload\" enctype=\"multipart/form-data\" target='frameFile'>请选择图片：<input name=\"fileUp\" id=\"fileUp\" type=\"file\" size=\"20\"> <input type=\"hidden\" value=\""+json[i].act_id+"\" name=\"id\"><input type=\"button\" value=\"提交\" onclick=\"submit()\">"
		
		//图片管理
		var image_manage = document.createElement("td");
		var image_manage_url =  document.createElement("a");
		image_manage_url.href="../admin/act_image.jsp?id="+json[i].act_id;
		image_manage_url.innerHTML="活动图片管理";
		image_manage.appendChild(image_manage_url);
		
		// 提交按钮
//		var submit = document.createElement("td");
//		submit.innerHTML = "<input type=\"button\" value=\"点击上传\">"
			
		tr.appendChild(id);
		tr.appendChild(act_title);
		tr.appendChild(act_startTime);
		tr.appendChild(act_endTime);
		tr.appendChild(act_status);
		tr.appendChild(aca_name);
		tr.appendChild(aca_man);
		tr.appendChild(act_enrollment);
		tr.appendChild(act_actual_enrollment);
		tr.appendChild(act_delete);
		tr.appendChild(act_add);
		tr.appendChild(act_update);
		tr.appendChild(uploadImage);
//		tr.appendChild(submit);
		tr.appendChild(image_manage);
		table.appendChild(tr);
	}
}