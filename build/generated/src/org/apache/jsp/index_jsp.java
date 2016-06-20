package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import cn.edu.hnuc.volunteer_Sys.util.checkLogin;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"./js/user/logout.js\"></script>\r\n");
      out.write("<link href=\"//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<script src=\"//cdn.bootcss.com/jquery/2.2.1/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\r\n");
      out.write("<title>志愿者服务平台</title>\r\n");
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
      out.write("                <a class=\"navbar-brand\" href=\"#\">志愿者服务平台</a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <!-- Collect the nav links, forms, and other content for toggling -->\r\n");
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
      out.write("    <div class=\"container\" id=\"ctn\">\r\n");
      out.write("    </div>\r\n");
      out.write("    <script>\r\n");
      out.write("        $.ajax({\r\n");
      out.write("            url:'/servlet/indexInfo',\r\n");
      out.write("            dataType:'json',\r\n");
      out.write("            method: 'get',\r\n");
      out.write("            success:function(data){\r\n");
      out.write("                console.log(data);\r\n");
      out.write("            },\r\n");
      out.write("            error:function(){\r\n");
      out.write("                console.log\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    </script>\r\n");
      out.write("</body>\r\n");
      out.write("<!--\t<br><label>基本活动展示，对所有用户开放:</label><br>\r\n");
      out.write("\t<p><a href=\"./activities?id=2\">这是第1个测试活动</a></p>\r\n");
      out.write("\t<p><a href=\"./activities?id=3\">这是第2个测试活动</a></p>\r\n");
      out.write("\t<p><a href=\"./activities?id=5\">这是第3个测试活动</a></p>\r\n");
      out.write("\t<p><a href=\"./activities?id=6\">这是第4个测试活动</a></p>\r\n");
      out.write("\t<p><a href=\"./activities?id=9\">这是第5个测试活动</a></p>\r\n");
      out.write("\t<p><a href=\"./activities?id=12\">这是第6个测试活动</a></p>\r\n");
      out.write("\t<p><a href=\"./activities?id=13\">这是第7个测试活动</a></p>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<hr>\r\n");
      out.write("\t<br><label><a href=\"FileServletTest.jsp\">图片上传测试：</a></label><br>\r\n");
      out.write("\t<p>普通管理员才可食用，上传图片后输入xxmodd.com:8080/image/，然后加上上传的文件名即可看到图片,还有务必使上传的文件名为数字或英文，因为中文会乱码。。</p>-->\r\n");
      out.write("\r\n");
      out.write("</html>");
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
