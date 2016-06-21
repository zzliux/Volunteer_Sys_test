package servlet;

/*
 * 向管理员展现活动
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JSON.JsonHelper;
import cn.edu.hnuc.volunteer_Sys.entity.Activity;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;
import com.google.gson.Gson;

/**
 * Servlet implementation class act_show
 */
@WebServlet("/act_show")
public class Admin_Acts_show extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Admin_Acts_show() {
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

		int userid = cn.edu.hnuc.volunteer_Sys.util.checkLogin.checkL(request,
				response);
		//只有普通管理员才能查看本院的活动
		if (userid == 2) {
			String adm_username = (String) request.getSession().getAttribute(
					"adm_username");// 获取管理员账号
			int academy_id = info_Query.admQuery(adm_username).getAcademy_id();
			ArrayList<Activity> activities = info_Query.actsQuery(academy_id);// 普通管理员查看本院活动信息
			PrintWriter pw = response.getWriter();
            pw.print((new Gson()).toJson(activities));
			pw.close();
		} else {
			PrintWriter pw = response.getWriter();
			pw.print("empty");
			pw.close();
		}
	}

}
