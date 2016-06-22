<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Activity"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Academy"%>
<%@page import="JSON.JsonHelper"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Students"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Students stu = null;
	Academy aca = null;
	if (checkLogin.checkL(request, response) != 1) {
		response.sendRedirect("../login.jsp");
	} else {
		stu = (Students) request.getSession().getAttribute("stuinfo");
		aca = new Academy();
		JsonHelper.toJavaBean(aca, stu.getAcademy_info().toString());
		//out.print(stu.toString());
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生个人信息</title>
<script type="text/javascript" src="/Volunteer_Sys_test/js/user/logout.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/Volunteer_Sys_test/js/layer/layer.js"></script>
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
            </div>
        </div>
    </nav>
	<%
		if (stu != null) {
	%>
    <div class="container">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="panel panel-default">
                    <div class="panel-heading">个人信息</div>
                        <ul class="list-group">
                            <li class="list-group-item">姓名：<%=stu.getStu_name()%></li>
                            <li class="list-group-item">学号：<%=stu.getStu_account()%></li>
                            <li class="list-group-item">性别：<%=stu.getStu_sex()%></li>
                            <li class="list-group-item">电话：<%=stu.getStu_phone()%></li>
                            <li class="list-group-item">QQ：<%=stu.getStu_qq()%></li>
                            <li class="list-group-item">邮箱：<%=stu.getStu_email()%></li>
                            <li class="list-group-item">学院：<%=aca.getAca_name()%></li>
                            <li class="list-group-item">学院负责人：<%=aca.getAca_man()%></li>
                            <li class="list-group-item">学院负责人电话：<%=aca.getAca_phone()%></li>
                        </ul>
                        <div class="panel-body">

                            <label>您所参加的活动:</label><br>
                            <!-- 已参加且正在进行的活动 -->
                            <div id="on">
                                <label>进行中:</label><br>
                                <%
                                    //获取该学生所参加的活动
                                        int num = 0;
                                        List<Activity> actList = stu.getActList();
                                        for (int i = 0; i < actList.size(); i++) {
                                            Activity act = actList.get(i);
                                            if (act.getAct_status() != 2) {
                                %>
                                <a href="../activities?id=<%=act.getAct_id()%>"><%=act.getAct_title()%></a><br>
                                <%
                                    num++;
                                            }
                                        }
                                        if (num == 0) {
                                            out.print("无");
                                        }
                                %>
                            </div>



                            <!-- 已参加但已结束的活动 -->
                            <div id="over">
                                <label>已结束:</label><br>
                                <%
                                    num = 0;
                                        for (int i = 0; i < actList.size(); i++) {
                                            Activity act = actList.get(i);
                                            if (act.getAct_status() == 2) {
                                %>
                                <a href="../activities?id=<%=act.getAct_id()%>"><%=act.getAct_title()%></a><br>
                                <%
                                    num++;
                                            }
                                        }
                                        if (num == 0) {
                                            out.print("无");
                                        }
                                %>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>