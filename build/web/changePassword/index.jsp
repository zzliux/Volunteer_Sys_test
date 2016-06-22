<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if(checkLogin.checkL(request, response)==0){
    	response.sendRedirect("../login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/user/changePsw.js"></script>
<script type="text/javascript" src="/Volunteer_Sys_test/js/user/logout.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/Volunteer_Sys_test/js/layer/layer.js"></script>
<title>修改密码</title>
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
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">修改密码</div>
                    <div class="panel-body">
                       <form class="form-horizontal">
                            <div class="form-group">
                                <label for="oldPsw" class="control-label col-sm-3">原始密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="oldPsw" class="form-control" placeholder="原始密码">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="newPsw" class="control-label col-sm-3">新密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="newPsw" class="form-control" placeholder="新密码">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="ConfirmPwd" class="control-label col-sm-3">确认新密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="ConfirmPwd" class="form-control" placeholder="确认新密码">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-9 col-sm-offset-3">
                                    <input type="submit" class="btn btn-default btn-block" value="提交">
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
             url: '/Volunteer_Sys_test/servlet/modify_UserPsw',
             method: 'post',
             data: {
                oldPsw: $('#oldPsw').val(),
                newPsw: $('#newPsw').val(),
                ConfirmPwd: $('#ConfirmPwd').val()
             },
             success: function(data){
                 layer.close(ind);
                 layer.msg(data);
             },
             error: function(){
                 layer.close(ind);
                 layer.msg('网络连接错误，请重新刷新本页');
             }
         });
         return false;
    });
</script>
</body>
</html>