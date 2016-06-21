<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生注册</title>
<script type="text/javascript" src="/Volunteer_Sys_test/js/user/logout.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/Volunteer_Sys_test/js/layer/layer.js"></script>
<script type="text/javascript">
	function reloadCode() {
		var time = new Date().getTime();
		document.getElementById("imagecode").src = "./randomcode?d=" + time;
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
                        + un + "</a></li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"/Volunteer_Sys_test/student/info.jsp\">查看基本信息</a></li>";
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
                    <div class="panel-heading">学生注册</div>
                    <div class="panel-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-4">账号信息</label>
                            </div>
                            <div class="form-group">
                                <label for="account" class="control-label col-sm-4">学号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="account" placeholder="学号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label col-sm-4">密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="password" placeholder="密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="confirm_password" class="control-label col-sm-4">确认密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="confirm_password" placeholder="确认密码">
                                </div>
                            </div>
                            <hr>
                            <div class="form-group">
                                <label class="control-label col-sm-4">基本信息</label>
                            </div>
                            <div class="form-group">
                                <label for="username" class="control-label col-sm-4">姓名</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="username" placeholder="姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="academy" class="control-label col-sm-4">学院</label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="academy">
                                        <option value="1">计算机与信息工程学院</option>
                                        <option value="2">外国语学院学院</option>
                                        <option value="3">会计学院</option>
                                        <option value="4">艺社</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="gender" class="control-label col-sm-4">性别</label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="gender">
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tel" class="control-label col-sm-4">电话</label>
                                <div class="col-sm-8">
                                    <input type="tel" class="form-control" id="tel" placeholder="电话">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="qq" class="control-label col-sm-4">QQ</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="qq" placeholder="QQ">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="control-label col-sm-4">电子邮箱</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="email" placeholder="电子邮箱">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="vcode" class="col-sm-4 control-label">验证码</label>
                                <div class="col-sm-8">
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
                            <hr>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-4">
                                    <input type="submit" class="btn btn-default btn-block" value="注册">
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
            url:'/Volunteer_Sys_test/servlet/register',
            method: 'post',
            data:{
                UserName: $('#username').val(),
                Account: $('#account').val(),
                PassWord: $('#password').val(),
                Confirm_Password: $('#confirm_password').val(),
                Gender: $('#gender').val(),
                Tel: $('#tel').val(),
                QQ: $('#qq').val(),
                Email: $('#email').val(),
                Academy: $('#academy').val(),
                CheckCode: $('#vcode').val(),
            },
            success: function(data){
                layer.close(ind);
                layer.msg(data);
            },
            error: function(){
               layer.close(ind);
               layer.msg('网络错误，请刷新后重试');
            }
        });
        return false;
    });
</script>
</body>
</html>