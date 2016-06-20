<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/activity/user_act_idshow.js"></script>
<script type="text/javascript" src="../js/student/stu_enlist.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
                + un + "</a></li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"./student/info.jsp\">查看基本信息</a></li>";
    } else if (user_statuscode == 2) {
        un = request.getSession().getAttribute("adm_username").toString();
        infoOut = "<li><a href=\"#\""
                + un + "</li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"./admin/act_manage.jsp\">本院活动管理</a></li><li><a href=\"./admin/stu_manage.jsp\">本院学生管理</a></li>" + "<li><a href=\"./admin/info.jsp\">查看基本信息</a></li>";
    } else if (user_statuscode == 3) {
        un = request.getSession().getAttribute("superadmin").toString();
        infoOut = "<li><a href=\"#\"" + un +  "</li><li><a href=\"javascript:logout();\">注销</a></li><li><a href=\"./superadmin/adm_manage.jsp\">管理管理员</a></li>";
    } else {
        un = "用户登录";
        infoOut = "<li><a href=\"#\"" + un +  "</li><li><a href=\"login.jsp\">普通用户/管理员</a></li><li><a href=\"./superadmin/login.jsp\">高级管理员</a></li>";
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
            <a class="navbar-brand" href="#">志愿者服务平台</a>
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
	<div id="box" >
		<h1>具体活动页面展示：</h1>
		<div id="image">
			图片展示：
		</div>
		<div id="title">活动标题：</div><br>
		<div id="sdate">开始日期：</div><br>
		<div id="edate">结束日期：</div><br>
		<div id="statu">活动状态：</div><br>
		<div id="name">创办学院：</div><br>
		<div id="allenrollment">报名人数上线：</div><br>
		<div id="enrollment">已报名人数：</div><br>
		<div id="content">活动内容：</div><br>
		<div id="enlist">
			<input type="button" onclick="enlist()" value="我要报名">
		</div>
	</div>
</body>
</html>