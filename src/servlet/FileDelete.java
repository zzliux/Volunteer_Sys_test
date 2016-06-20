package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hnuc.volunteer_Sys.util.DeleteFileUtil;
import cn.edu.hnuc.volunteer_Sys.util.act_imgurl_Update;
import cn.edu.hnuc.volunteer_Sys.util.checkLogin;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;

@WebServlet("/FileDelete")
public class FileDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String deletePath = "/var/lib/tomcat7/webapps/Volunteer_Sys_test/image/";//服务器删除目录
//	private static String deletePath = "D:\\temp/";// 本地删除目录

	public FileDelete() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb = new StringBuffer();

		int userid = checkLogin.checkL(request, response);
		if (userid == 2) {
			// 获取管理员信息
			String adm_username = (String) request.getSession().getAttribute(
					"adm_username");
			int adm_aca_id = info_Query.admQuery(adm_username).getAcademy_id();
			int act_id;
			String fileName = null;
			try {
				// 获取post数据
				act_id = Integer.parseInt(request.getParameter("act_id"));
				fileName = request.getParameter("fileName");
				// 更新数据库活动图片地址信息
				if (act_imgurl_Update.delete(act_id, adm_aca_id, fileName)) {
					// 删除对应图片文件
					DeleteFileUtil.deleteFile(deletePath + fileName);
					sb.append("删除成功！");
				}else{
					sb.append("删除失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				sb.append("error!");
			}
		} else {
			response.sendRedirect("../login.jsp");
			sb.append("请登入！");
		}
		PrintWriter pw = response.getWriter();
		pw.print(sb.toString());
		pw.close();
	}
}
