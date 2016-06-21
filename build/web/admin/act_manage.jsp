<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if(checkLogin.checkL(request, response)!=2){
		response.sendRedirect("../login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/activity/admin_act_delete.js"></script>
<script type="text/javascript" src="/Volunteer_Sys_test/js/user/logout.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="/Volunteer_Sys_test/js/layer/layer.js"></script>
<title>活动管理页面</title>
<script type="text/javascript">
function submit() {
	$('#formFile').submit();
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
        <div class="panel panel-default">
            <div class="panel-heading">活动管理</div>
            <table class="table" id="table">
				<tr>
					<th>#</th>
					<th>活动标题</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>活动状态</th>
					<th>学院名称</th>
					<th>学院负责人</th>
					<th>最大报名人数</th>
					<th>已报名人数</th>
					<th>操作</th>
					<th>操作</th>
                    <th>操作</th>
				</tr>
            </table>
            <div class="panel-body">
                <td><a href="./actadd">添加活动</a></td>
            </div>
        </div>
    </div>
    <script>
        var ind = layer.msg('加载中', {icon: 16});
        var act_data;
        $.ajax({
            url: '/Volunteer_Sys_test/servlet/admin_Acts_show',
            method: 'post',
            dataType:'json',
            data:{},
            success: function(data){
                console.log(data);
                act_data = data;
                var status;
                for(var i=0; i<data.length; i++){
                    if (data[i].act_status === 1) {
                        status = "进行中";
                    } else if (data[i].act_status === 2) {
                        status = "已结束";
                    } else {
                        status = "未开始";
                    }
                    $('#table').append('<tr><td>' + (i+1) +  '</td><td>' + data[i].act_title + '</td><td>' + data[i].act_startTime + '</td><td>' + data[i].act_endTime + '</td><td>' + status + '</td><td>' + data[i].academy_info.myHashMap.aca_name + '</td><td>' + data[i].academy_info.myHashMap.aca_man + '</td><td>' + data[i].act_enrollment + '</td><td>' + data[i].act_actual_enrollment + '</td><td><button class="btn btn-default" onclick="lookStu(' + data[i].act_id + ')">查看报名学生</button></td><td><button class="btn btn-default" onclick="act_delete(' + data[i].act_id + ')">删除</button></td><td><a class="btn btn-default" href="./actupdate?act_id=' + data[i].act_id + '">修改</a></td></tr>');
                }
                layer.close(ind);
            },
            error: function(){
                layer.msg('网络错误，请重新刷新本页');
            }
        });
        function lookStu(id){
            for(var i=0;i<act_data.length;i++){
                if(id === act_data[i].act_id)
                    break;
            }
            var out = [];
            for(var j=0;j<act_data[i].stuList.length; j++){
                out.push(act_data[i].stuList[j].stu_name);
            }
            layer.alert(out.join('， '));
        }
    </script>
</body>
</html>