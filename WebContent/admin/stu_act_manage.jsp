<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Activity"%>
<%@page import="java.util.List"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.info_Query"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Students"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (checkLogin.checkL(request, response) != 2) {
		response.sendRedirect("../login.jsp");
	}else{
		String aut = null;
		Students stu = null;
		try{
			aut = request.getParameter("account");
			stu = info_Query.stuAutQuery(aut);
		

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/student/admin_stu_act_delete.js"></script>
<script type="text/javascript" src="/Volunteer_Sys_test/js/user/logout.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/Volunteer_Sys_test/js/layer/layer.js"></script>
<title>学生报名活动管理</title>
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
            <div class="col-sm-8 col-sm-offset-2">
                <div class="panel panel-default"</div>
                    <div class="panel-heading">学生信息</div>
                    <div class="panel-body">
                        <label>学生基本信息</label><br>
            <p>姓名：<%=stu.getStu_name()%></p>
            <p>学号：<%=stu.getStu_account()%></p>
            <p>性别：<%=stu.getStu_sex()%></p>
            <p>电话：<%=stu.getStu_phone()%></p>
            <p>QQ：<%=stu.getStu_qq()%></p>
            <p>邮箱：<%=stu.getStu_email()%></p>
            <hr>
            <label>该学生所参加的活动：</label><br>

                    </div>
                <table class="table">
                    <tr>
                        <th>#</th>
                        <th>活动标题</th>
                        <th>活动状态</th>
                        <th>操作</th>
                    </tr>
                <%
                    List<Activity> actList = stu.getActList();
                    for (int i = 0; i < actList.size(); i++) {
                        Activity act = actList.get(i);
                        String act_status;
                        int status = act.getAct_status();
                        if(status==1){
                            act_status = "进行中";
                        }else if(status==2){
                            act_status = "已结束";
                        }else{
                            act_status = "未开始";
                        }
                %>
                    <tr>
                        <td><%=i+1%></td>
                        <td><%=act.getAct_title() %></td>
                        <td><%=act_status %></td>
                        <td><input type="button" value="删除" class="btn btn-default" onclick="stu_act_delete(<%=stu.getStu_id()%>,<%=act.getAct_id()%>)"></td>
                    </tr>
                <% 
                    }
                %>
                </table>
            </div>
        </div>
    </div>
	
</body>
</html>

<%
        }catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("./stu_manage.jsp");
        }
    } 
%>
