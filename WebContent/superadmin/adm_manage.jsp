<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.GOTO"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.superadminVerification"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.info_Query"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Admins"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.code.kaptcha.Constants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>超级管理员管理页面</title>
<script type="text/javascript" src="/Volunteer_Sys_test/js/admin/sup_admin_delete.js"></script>
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
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
        <%
            //检测是否已登入
            if (checkLogin.checkL(request, response) == 3) {
        %>

        <div class="container">
            <div class="panel panel-default">
                <div class="panel-heading">普通管理员账号管理</div>
                <%= showAdmin() %>
            </div>
        </div>
        <%
        } else {
            String super_username = null;
            String super_password = null;
            String checkcode = null;

            try {
                super_username = request.getParameter("UserName");
                super_password = request.getParameter("PassWord");
                checkcode = request.getParameter("CheckCode");
                // 获取正确的验证码
                String ccode = (String) request.getSession().getAttribute(
                        Constants.KAPTCHA_SESSION_KEY);
                // System.out.println(ccode+","+CheckCode);
                if (ccode.equals(checkcode)) {
                    //检测密码
                    if (superadminVerification.checkSupadmin_psw(
                            super_username, super_password)) {
                        //设置超级管理员身份
                        request.getSession().setAttribute("superadmin", super_username);
                        response.sendRedirect("adm_manage.jsp");
                    } else {
                        out.print("<script type=\"text/javascript\">alert(\"用户名或密码错误！\");window.location=\"login.jsp \";</script>");
                    }
                } else {
                    out.print("<script type=\"text/javascript\">alert(\"验证码错误！\");window.location=\"login.jsp \";</script>");
                }

            } catch (Exception e) {
                //e.printStackTrace();
                response.sendRedirect("login.jsp");
            }
        }
    %>
    <%!public static String showAdmin() {
        //获取管理员信息
        ArrayList<Admins> admins = info_Query.adminsQuery();
        //创建管理员表格
        int id = 1;//序号
        StringBuffer sb = new StringBuffer();
        sb.append("<table class=\"table\">");
        sb.append("<tr><th>序号</th><th>用户名</th><th>学院</th><th>学院负责人</th><th>联系电话</th><th>操作</th></tr>");
        for (Admins adm : admins) {
            sb.append("<tr><td>"
                    + (id++)
                    + "</td><td>"
                    + adm.getAdm_username()
                    + "</td><td>"
                    + adm.getAcademy_info().getAca_name()
                    + "</td><td>"
                    + adm.getAcademy_info().getAca_man()
                    + "</td><td>"
                    + adm.getAcademy_info().getAca_phone()
                    + "</td><td>"
                    + "<input type=\"button\" value=\"删除\" class=\"btn btn-default\" onclick=\"admin_delete("
                    + adm.getAdm_id() + ")\""
                    + "</td><td>"+
                    "</td></tr>");
        }
        sb.append("</table><ul class=\"list-group\"><li class=\"list-group-item\"><a href=\"adminadd\">添加管理员</a></li></ul>");
        return sb.toString();
    }%>
</body>
</html>
