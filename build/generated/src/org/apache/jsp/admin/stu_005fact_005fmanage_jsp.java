package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import cn.edu.hnuc.volunteer_Sys.entity.Activity;
import java.util.List;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;
import cn.edu.hnuc.volunteer_Sys.entity.Students;
import cn.edu.hnuc.volunteer_Sys.util.checkLogin;

public final class stu_005fact_005fmanage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	if (checkLogin.checkL(request, response) != 2) {
		response.sendRedirect("../login.jsp");
	}else{
		String aut = null;
		Students stu = null;
		try{
			aut = request.getParameter("account");
			stu = info_Query.stuAutQuery(aut);
		


      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/student/admin_stu_act_delete.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/Volunteer_Sys_test/js/user/logout.js\"></script>\r\n");
      out.write("<link href=\"//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<script src=\"//cdn.bootcss.com/jquery/2.2.1/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"/Volunteer_Sys_test/js/layer/layer.js\"></script>\r\n");
      out.write("<title>学生报名活动管理</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <nav class=\"navbar navbar-default\">\r\n");
      out.write("        ");

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
        
      out.write("\r\n");
      out.write("        <div class=\"container-fluid\">\r\n");
      out.write("            <!-- Brand and toggle get grouped for better mobile display -->\r\n");
      out.write("            <div class=\"navbar-header\">\r\n");
      out.write("                <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\r\n");
      out.write("                    <span class=\"sr-only\">Toggle navigation</span>\r\n");
      out.write("                    <span class=\"icon-bar\"></span>\r\n");
      out.write("                    <span class=\"icon-bar\"></span>\r\n");
      out.write("                    <span class=\"icon-bar\"></span>\r\n");
      out.write("                </button>\r\n");
      out.write("                <a class=\"navbar-brand\" href=\"/Volunteer_Sys_test/\">志愿者服务平台</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\r\n");
      out.write("                <ul class=\"nav navbar-nav navbar-right\">\r\n");
      out.write("                    <li class=\"dropdown\">\r\n");
      out.write("                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">");
      out.print( un );
      out.write(" <span class=\"caret\"></span></a>\r\n");
      out.write("                        <ul class=\"dropdown-menu\">\r\n");
      out.write("                            ");
      out.print( infoOut );
      out.write("\r\n");
      out.write("                        </ul>\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </div><!-- /.navbar-collapse -->\r\n");
      out.write("        </div><!-- /.container-fluid -->\r\n");
      out.write("    </nav>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"col-sm-8 col-sm-offset-2\">\r\n");
      out.write("                <div class=\"panel panel-default\">\r\n");
      out.write("                    <div class=\"panel-heading\">学生信息</div>\r\n");
      out.write("                    <div class=\"panel-body\">\r\n");
      out.write("                        <label>学生基本信息</label><br>\r\n");
      out.write("                            <p>姓名：");
      out.print(stu.getStu_name());
      out.write("</p>\r\n");
      out.write("                            <p>学号：");
      out.print(stu.getStu_account());
      out.write("</p>\r\n");
      out.write("                            <p>性别：");
      out.print(stu.getStu_sex());
      out.write("</p>\r\n");
      out.write("                            <p>电话：");
      out.print(stu.getStu_phone());
      out.write("</p>\r\n");
      out.write("                            <p>QQ：");
      out.print(stu.getStu_qq());
      out.write("</p>\r\n");
      out.write("                            <p>邮箱：");
      out.print(stu.getStu_email());
      out.write("</p>\r\n");
      out.write("                            <hr>\r\n");
      out.write("                            <label>该学生所参加的活动：</label><br>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <table class=\"table\">\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <th>#</th>\r\n");
      out.write("                            <th>活动标题</th>\r\n");
      out.write("                            <th>活动状态</th>\r\n");
      out.write("                            <th>操作</th>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                    ");

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
                    
      out.write("\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>");
      out.print(i+1);
      out.write("</td>\r\n");
      out.write("                            <td>");
      out.print(act.getAct_title() );
      out.write("</td>\r\n");
      out.write("                            <td>");
      out.print(act_status );
      out.write("</td>\r\n");
      out.write("                            <td><input type=\"button\" value=\"删除\" class=\"btn btn-default\" onclick=\"stu_act_delete(");
      out.print(stu.getStu_id());
      out.write(',');
      out.print(act.getAct_id());
      out.write(")\"></td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                    ");
 
                        }
                    
      out.write("\r\n");
      out.write("                    </table>\r\n");
      out.write("                </table>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");

        }catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("./stu_manage.jsp");
        }
    } 

      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
