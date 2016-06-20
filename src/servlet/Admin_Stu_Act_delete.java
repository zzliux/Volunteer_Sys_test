package servlet;
/*
 * 删除学生所参加的活动
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hnuc.volunteer_Sys.util.info_Query;
import cn.edu.hnuc.volunteer_Sys.util.info_Update;
@WebServlet("/Admin_Stu_Act_delete")
public class Admin_Stu_Act_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_Stu_Act_delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String msg;
		int userid = cn.edu.hnuc.volunteer_Sys.util.checkLogin.checkL(request,
				response);
		if(userid==2){
			int stu_id;
			int act_id;
			int academy_id;
			try {
				stu_id = Integer.parseInt(request.getParameter("stu_id"));
				act_id = Integer.parseInt(request.getParameter("act_id"));
				
				// 获取管理员所属学院号
				String adm_username = (String) request.getSession().getAttribute(
						"adm_username");
				academy_id = info_Query.admQuery(adm_username).getAcademy_id();
				if(info_Update.stu_act_delete(stu_id, act_id, academy_id)){
					msg="删除成功！";
				}else{
					msg="删除失败！";
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				msg="error!";
			}
		}else{
			 msg="对不起，你没有权限！";
		}
		PrintWriter pw = response.getWriter();
		pw.print(msg);
		pw.close();
	}
}
