<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" charset="utf-8" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>用户登录</title>
<script type="text/javascript" src="/Volunteer_Sys_test/js/user/logout.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/Volunteer_Sys_test/js/layer/layer.js"></script>
<script type="text/javascript">
function reloadCode(){
	var time = new Date().getTime();
		document.getElementById("imagecode").src ="./randomcode?d=" + time;
}
</script>
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
                        + un + "</a></li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"/Volunteer_Sys_test/student/info.jsp\">查看基本信息</a></li><li><a href=\"/Volunteer_Sys_test/changePassword/\">修改密码</a></li>";
            } else if (user_statuscode == 2) {
                un = request.getSession().getAttribute("adm_username").toString();
                infoOut = "<li><a href=\"#\""
                        + un + "</li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"/Volunteer_Sys_test/admin/act_manage.jsp\">本院活动管理</a></li><li><a href=\"/Volunteer_Sys_test/admin/stu_manage.jsp\">本院学生管理</a></li><li><a href=\"/Volunteer_Sys_test/changePassword/\">修改密码</a></li>";
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
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">用户登录</div>
                    <div class="panel-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="username" class="col-sm-3 control-label">用户名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="username" placeholder="用户名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-3 control-label" >密　码</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="password" placeholder="密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="vcode" class="col-sm-3 control-label">验证码</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="vcode" placeholder="验证码">
                                        <span class="input-group-addon">
                                            <a href="javascript:reloadCode();">
                                                <img id="imagecode" alt="验证码" style="margin: -8px -13px;height: 34px;border-radius: 5px;" src="./randomcode">
                                            </a>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="user" class="col-sm-3 control-label">身　份</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="user">
                                        <option value="1">管理员</option>
                                        <option value="2">学生</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-9 col-sm-offset-3">
                                    <input type="submit" class="btn btn-default btn-block" value="登录">
                                    <div style="margin-top:10px"><a href="register.jsp">学生注册</a></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
    $('form').submit(function(){
        var ind = layer.msg('加载中', {icon: 16});
        $.ajax({
            url: '/Volunteer_Sys_test/servlet/login',
            method: 'post',
            data: {
                Userid: $('#user').val(),
                UserName: $('#username').val(),
                PassWord: $('#password').val(),
                CheckCode: $('#vcode').val()
            },
            success: function(data){
                layer.close(ind);
                layer.msg(data);
                if(data.match(/登入成功/)){
                    setTimeout(function(){
                        location.href = '/Volunteer_Sys_test/';
                    },1000);
                }
            },
            error: function(){
                layer.msg('网络错误，请刷新后重试');
            }
        });
        return false;
    });
</script>
</body>
</html>