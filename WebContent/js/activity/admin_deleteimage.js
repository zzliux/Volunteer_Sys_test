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
	//删除图片
	function imagedelete(act_id,fileName){
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("POST", '../servlet/fileDelete', true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttp.send("act_id="+act_id+"&fileName="+fileName);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var text = xmlHttp.responseText;
				alert(text);
				document.location.reload();
			}
		}
	}
	
	var box = document.getElementById("box");

	// 图片
	var imageUrls = json.act_imgurl.split(',');
	for(var i=0;i<imageUrls.length-1;i++){
		var fileName = imageUrls[i];
		var div = document.createElement("div");
		// 图片
		var image = document.createElement("img");
		image.src = "http://xxmodd.com:8080/Volunteer_Sys_test/image/"+fileName;
		image.alt = "哎呀，显示不出来辣！";
		document.getElementById("image").appendChild(image);
		// 删除按钮
		var button = document.createElement("input");
		button.type = "button";
		button.value = "删除";
		button.onclick = function(){
//			console.log("fileName="+fileName);
			imagedelete(json.act_id,fileName);
		};
		div.appendChild(image);
		div.appendChild(button);
		box.appendChild(div);
//		document.getElementById(imageUrls[i]).onclick= function(){
//			imagedelete(json.act_id,imageUrls[i]);
//		}
	}
	
	
	// 标题
	var act_title = document.createElement("label");
	act_title.innerHTML = json.act_title;

	document.getElementById("title").appendChild(act_title);
}