function submit() {
    Userid = User.options[User.selectedIndex].value;
    UserName = document.getElementById("UserName").value;
    password = document.getElementById("PassWord").value;
    checkcode = document.getElementById("CheckCode").value;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", './servlet/login', true);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send('Userid=' + Userid + '&UserName=' + UserName + '&PassWord=' + password + "&CheckCode=" + checkcode);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var text = xmlHttp.responseText;
            alert(text);
            if(text.indexOf("成功")!==-1){
                window.location.href="index.jsp"; 
            }else{
                document.location.reload();
            }
        }
    };
}
