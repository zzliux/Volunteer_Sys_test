function stu_act_delete(stu_id,act_id){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", '../servlet/admin_Stu_Act_delete', true);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send('stu_id='+stu_id+'&act_id='+act_id);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = xmlHttp.responseText;
            alert(text);
            document.location.reload();
        }
    };
}