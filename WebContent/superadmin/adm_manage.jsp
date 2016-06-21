<%@page import="cn.edu.hnuc.volunteer_Sys.util.checkLogin"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.GOTO"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.superadminVerification"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.util.info_Query"%>
<%@page import="cn.edu.hnuc.volunteer_Sys.entity.Admins"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.code.kaptcha.Constants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>超级管理员管理页面</title>
<script type="text/javascript" src="/Volunteer_Sys_test/js/admin/sup_admin_delete.js"></script>

</head>
<body>
	<%
		//检测是否已登入
		if (checkLogin.checkL(request, response) == 3) {
			out.print(showAdmin());
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
						request.getSession().setAttribute("superadmin",
								super_username);
						showAdmin();
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
		sb.append("<table align=\"center\"  style=\"width:600px;\" border=\"1\">");
		sb.append("<tr><th>序号</th><th>用户名</th><th>学院</th><th>学院负责人</th><th>联系电话</th><th>操作</th><th>操作</th><th>操作</th></tr>");
		for (Admins adm : admins) {
			sb.append("<tr><th>"
					+ (id++)
					+ "</th><th>"
					+ adm.getAdm_username()
					+ "</th><th>"
					+ adm.getAcademy_info().getAca_name()
					+ "</th><th>"
					+ adm.getAcademy_info().getAca_man()
					+ "</th><th>"
					+ adm.getAcademy_info().getAca_phone()
					+ "</th><th>"
					+ "<input type=\"button\" value=\"删除\" onclick=\"admin_delete("
					+ adm.getAdm_id() + ")\""
					+ "</th><th>" +
					"<a href=\"adminupdate?adm_id="+adm.getAdm_id()+"\">修改</a>"
					+ "</th><th>"+
					"<a href=\"adminadd\">添加</a>"+
					"</th></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}%>


</body>
</html>