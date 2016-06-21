<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/Volunteer_Sys_test/js/user/logout.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/Volunteer_Sys_test/js/layer/layer.js"></script>
<style>
    .act-content {
        min-height: 100px;
    }
</style>
<title>活动展示页面</title>
</head>
<body>
    <nav class="navbar navbar-default">
        <%
            int user_statuscode = checkLogin.checkL(request, response);
            String infoOut;
            String un = "";
            if (user_statuscode == 1) {
                un = request.getSession().getAttribute("stu_account").toString();
                infoOut = "<li><a href=\"#\">"
                        + un + "</a></li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"/Volunteer_Sys_test/student/info.jsp\">查看基本信息</a></li>";
            } else if (user_statuscode == 2) {
                un = request.getSession().getAttribute("adm_username").toString();
                infoOut = "<li><a href=\"#\""
                        + un + "</li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"/Volunteer_Sys_test/admin/act_manage.jsp\">本院活动管理</a></li><li><a href=\"/Volunteer_Sys_test/admin/stu_manage.jsp\">本院学生管理</a></li>" + "<li><a href=\"/Volunteer_Sys_test/admin/info.jsp\">查看基本信息</a></li>";
            } else if (user_statuscode == 3) {
                un = request.getSession().getAttribute("superadmin").toString();
                infoOut = "<li><a href=\"#\"" + un +  "</li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"/Volunteer_Sys_test/superadmin/adm_manage.jsp\">管理管理员</a></li>";
            } else {
                un = "用户登录";
                infoOut = "<li><a href=\"#\"" + un +  "</li><li><a href=\"/Volunteer_Sys_test/login.jsp\">普通用户/管理员</a></li><li><a href=\"/Volunteer_Sys_test/superadmin/login.jsp\">高级管理员</a></li>";
            }
        %>
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/Volunteer_Sys_test/">志愿者服务平台</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%= un %> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <%= infoOut %>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <div class="container">
        <div class="panel panel-default panel-body">
            <div class="act-title" id="act-title"></div>
            <hr>
            <div class="act-content" id="act-content"></div>
            <hr>
            <div class="act-time" id="act-time"></div>
            <div class="act-stauts" id="act-stauts"></div>
            <div class="act-academy" id="act-academy"></div>
            <div class="act-num" id="act-num"></div>
            <hr>
            <button class="btn btn-default btn-block" onclick="enlist()">我要报名</button>
        </div>
    </div>
<script>
    var load = layer.msg('加载中', {icon: 16});
    var getId = location.href.match(/\?id=(\d+)/)[1];
    $.ajax({
        url:'/Volunteer_Sys_test/servlet/basicAct_show',
        method: 'post',
        data:{
            act_id:getId
        },
        dataType:'json',
        success: function(data){
            
            var status;
            if (data.act_status === "1") {
                status = "进行中";
            } else if (data.act_status === "2") {
                status = "已结束";
            } else {
                status = "未开始";
            }
            console.log(data);
            $('#act-title').html('<h3>'+data.act_title+'</h3>');
            $('#act-content').html(data.act_content);
            $('#act-time').html('活动时间：　' + data.act_startTime + '　至　'　+　data.act_endTime);
            $('#act-academy').html('创办学院：　' + JSON.parse(data.academy_info).aca_name);
            $('#act-stauts').html('活动状态：　' + status);
            $('#act-num').html('报名人数：　' + data.act_actual_enrollment + '/' + data.act_enrollment);
            
            layer.close(load);
        },
        error: function(){
            layer.msg('网络错误，请重新刷新本页');
        }
    });
    function enlist() {
        var ind2 = layer.msg('加载中', {icon: 16});
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("POST", '../servlet/stu_enlist', true);
        xmlHttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
        xmlHttp.send("act_id="+getId);//这是个测试
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var text = xmlHttp.responseText;
                if (text == "error") {
                    layer.close(ind2);
                    layer.msg('你还没登录呐！马上给你跳转到登录页面~~');
                    setTimeout(function(){
                        location.href = "../login.jsp";
                    },1000);
                } else {
                    layer.msg(text);
                }
            }
        }
    }
</script>
</body>
</html>