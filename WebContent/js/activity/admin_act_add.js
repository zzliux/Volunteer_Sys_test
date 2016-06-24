function act_add() {
    // 获取基本数据
    var act_title = document.getElementById("title").value;
    var act_content = document.getElementById("content").value;
    var act_startTime = document.getElementById("sdate").value;
    var act_endTime = document.getElementById("edate").value;
    var act_enrollment = document.getElementById("enrollment").value;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", '../../servlet/admin_Acts_update', true);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send('act_title=' + act_title + '&act_content=' + act_content + '&act_startTime=' + act_startTime + '&act_endTime=' + act_endTime + '&act_enrollment=' + act_enrollment + '&action=2');
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = xmlHttp.responseText;
            alert(text);
            if(text === "添加成功！"){
                window.location.href="../act_manage.jsp";
            }
        }
    };
}