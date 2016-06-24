function act_delete(act_Id){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", '../servlet/admin_Acts_update', true);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send('act_Id='+act_Id+'&action=0');
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = xmlHttp.responseText;
            alert(text);
            document.location.reload();
        }
    };
}