package servlet;

/*
 * 展示用户基本信息
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import JSON.JsonHelper;
import cn.edu.hnuc.volunteer_Sys.entity.Admins;
import cn.edu.hnuc.volunteer_Sys.entity.Students;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;

@WebServlet("/Stu_infomation")
public class User_info_bak extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public User_info_bak() {
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
		JSONObject js = null;
		String Account = null;
		Students s = null;
		String adm_username = null;
		Admins admin = null;
		// System.out.println(userid);
		if (userid == 1) {
			Account = (String) request.getSession().getAttribute("stu_account");
			s = info_Query.stuAutQuery(Account);
			request.getSession().setAttribute("stuinfo", s);
		} else if (userid == 2) {
			adm_username = (String) request.getSession().getAttribute(
					"adm_username");
			admin = info_Query.admQuery(adm_username);
			request.getSession().setAttribute("adminfo", admin);
		}
		
		if (s != null) {
			js = JsonHelper.toJSON(s);
		} else if (admin != null) {
			js = JsonHelper.toJSON(admin);
		}
		PrintWriter pw = response.getWriter();
		if (js != null) {
			pw.print(js.toString());
		} else {
			pw.print("empty");
		}
		pw.close();
	}
}
