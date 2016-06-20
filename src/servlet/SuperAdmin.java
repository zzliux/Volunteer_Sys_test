package servlet;

/*
 * 超级管理员对普通管理员进行增删改
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

@WebServlet("/SuperAdmin")
public class SuperAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SuperAdmin() {
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

		String updatemsg = null;
		int userid = cn.edu.hnuc.volunteer_Sys.util.checkLogin.checkL(request,
				response);

		// 超级管理员才能管理普通管理员
		if (userid == 3) {
			String action = null;
			try {
				action = request.getParameter("action");
			} catch (Exception e) {
				// System.out.println("用户没有提交action");
				updatemsg = "error";
			}
			// 0表示执行删除操作，1表示执行修改操作，2表示添加操作
			if (action != null) {
				// System.out.println(action);
				if (action.equals("0")) {
					updatemsg = delete(request);
				} else if (action.equals("1")) {
					updatemsg = update(request);
				} else if (action.equals("2")) {
					updatemsg = add(request);
				} else {
					updatemsg = "error！";
				}
			} else {
				updatemsg = "error！";
			}
		} else {
			updatemsg = "请登入！";
		}
		PrintWriter pw = response.getWriter();
		pw.print(updatemsg);
		pw.close();
	}

	// 增加管理员
	private String add(HttpServletRequest request) {
		String adm_uname = null;
		String adm_pwd = null;
		int aca_id;
		try {
			adm_uname = request.getParameter("adm_username");
			adm_pwd = request.getParameter("adm_pwd");
			aca_id = Integer.parseInt(request.getParameter("academy_id"));
			if (CheckUname(adm_uname)) {
				if (CheckAca(aca_id)) {
					if (info_Update.addAdmin(adm_uname, adm_pwd, aca_id)) {
						return "添加成功！";
					} else {
						return "添加失败";
					}
				} else {
					return "每个学院只能设置一个管理员,请重新选择学院！";
				}
			} else {
				return "该管理员账号已存在,请重新输入账号！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "添加失败";
		}

	}

	// 修改管理员信息，包括密码
	private String update(HttpServletRequest request) {
		int id;
		String adm_uname = null;
		String adm_psw = null;
		int aca_id;
		try {
			id = Integer.parseInt(request.getParameter("adm_id"));
			adm_uname = request.getParameter("adm_username");
			adm_psw = request.getParameter("adm_pwd");
			aca_id = Integer.parseInt(request.getParameter("academy_id"));
			if (CheckUname(adm_uname)) {
				if (info_Query.adm_acaQuery(aca_id).getAdm_id()==id) {
					if (info_Update.updateAdmin(id, adm_uname, adm_psw, aca_id)) {
						return "修改成功！";
					} else {
						return "修改失败！";
					}
				} else {
					return "每个学院只能设置一个管理员,请重新选择学院！";
				}
			} else {
				return "该管理员账号已存在,请重新输入账号！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "修改失败！";
		}
	}

	// 删除管理员
	private String delete(HttpServletRequest request) {
		int id;
		try {
			id = Integer.parseInt(request.getParameter("adm_id"));
			if (info_Update.deleteAdmin(id)) {
				return "删除成功！";
			} else {
				return "删除失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败";
		}
	}

	// 检测管理员账号是否存在
	private boolean CheckUname(String adm_uname) {
		if (info_Query.admQuery(adm_uname) == null) {
			return true;
		}
		return false;
	}

	// 检测学院是否已设置管理员
	private boolean CheckAca(int aca_id) {
		// 该管理员账号不存在，且该学院没有设置管理员
		if (info_Query.adm_acaQuery(aca_id)==null) {
			return true;
		}
		return false;
	}
}
