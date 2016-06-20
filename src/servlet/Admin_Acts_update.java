package servlet;

/*
 * 普通管理员对活动进行增删改
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hnuc.volunteer_Sys.util.info_Query;
import cn.edu.hnuc.volunteer_Sys.util.info_Update;

@WebServlet("/Act_update")
public class Admin_Acts_update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Admin_Acts_update() {
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

		// 普通管理员才能执行以下操作
		if (userid == 2) {
			String action = null;
			try {
				action = request.getParameter("action");
			} catch (Exception e) {
				System.out.println("用户没有提交action");
				updatemsg = "error";
			}
			// 0表示执行删除操作，1表示执行修改操作，2表示添加操作
			if (action != null) {
				//System.out.println(action);
				if (action.equals("0")) {
					if(delete(request)){
						updatemsg = "删除成功！";
					}else{
						updatemsg = "删除失败！";
					}
				} else if (action.equals("1")) {
					if(update(request)){
						updatemsg = "修改成功！";
					}else{
						updatemsg = "修改失败！";
					}
				} else if (action.equals("2")) {
					if(add(request)){
						updatemsg = "添加成功！";
					}else{
						updatemsg = "添加失败！";
					}
				} else {
					updatemsg = "error!";
				}
			} else {
				updatemsg = "error";
			}
		} else {
			updatemsg = "请登入";
		}
		PrintWriter pw = response.getWriter();
		pw.print(updatemsg);
		pw.close();
	}

	// 修改 ：前端发送数据：act_Id(活动id)，action(请求行为) title(标题) content(内容),
	// startDate(开始日期),
	// endDate(结束日期),act_enrollment(总报名人数)
	private boolean update(HttpServletRequest request) {
		int id;
		String title = null;
		String content = null;
		Date startDate = null;
		Date endDate = null;
		int academy_id;
		int act_enrollment;
		// String act_imgurl = null;
		try {
			// 获取基本信息
			id = Integer.parseInt(request.getParameter("act_id"));
			title = request.getParameter("act_title");
			content = request.getParameter("act_content");
			startDate = Date.valueOf(request.getParameter("act_startTime"));
			endDate = Date.valueOf(request.getParameter("act_endTime"));
			act_enrollment = Integer.parseInt(request
					.getParameter("act_enrollment"));
//			//测试接收数据
//			System.out.println(id);
//			System.out.println(title);
//			System.out.println(content);
//			System.out.println(startDate.toString());
//			System.out.println(endDate.toString());
//			System.out.println(act_enrollment);
//			
			
			
			// 获取学院号
			String adm_username = (String) request.getSession().getAttribute(
					"adm_username");
			academy_id = info_Query.admQuery(adm_username).getAcademy_id();
			//---------后台已动态修改活动状态
			/*
			// 求出活动状态 ,0表示未开始，1表示正在进行，2表示已结束
			long nowTime = new java.util.Date().getTime();// 获取当前时间
			if (startDate.getTime() > nowTime) {
				act_status = 0;
			} else if (startDate.getTime() <= nowTime
					&& nowTime <= endDate.getTime()) {
				act_status = 1;
			} else {
				act_status = 2;
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return info_Update.updateAct(id, title, content, startDate, endDate,
				academy_id, act_enrollment);
	}

	// 删除： 前端发送数据：act_Id(活动id)，action(请求行为)
	private boolean delete(HttpServletRequest request) {
		int id;
		int academy_id;
		try {
			id = Integer.parseInt(request.getParameter("act_Id"));
			// 获取学院号
			String adm_username = (String) request.getSession().getAttribute(
					"adm_username");
			academy_id = info_Query.admQuery(adm_username).getAcademy_id();
		} catch (Exception e) {
			//System.out.println("用户没有提交id");
			return false;
		}
		return info_Update.deleteAct(id, academy_id);
	}

	// 添加： 前端发送数据：action(请求行为) title(标题) content(内容), startDate(开始日期),
	// endDate(结束日期),act_enrollment(总报名人数)
	private boolean add(HttpServletRequest request) {
		String title = null;
		String content = null;
		Date startDate = null;
		Date endDate = null;
		int academy_id;
		int act_enrollment;
		// String act_imgurl = null;
		try {
			// 获取基本信息
			title = request.getParameter("act_title");
			content = request.getParameter("act_content");
			startDate = Date.valueOf(request.getParameter("act_startTime"));
			endDate = Date.valueOf(request.getParameter("act_endTime"));
			act_enrollment = Integer.parseInt(request
					.getParameter("act_enrollment"));
			//测试接收数据
//			System.out.println(title);
//			System.out.println(content);
//			System.out.println(startDate.toString());
//			System.out.println(endDate.toString());
//			System.out.println(act_enrollment);
			
			
			// 获取学院号
			String adm_username = (String) request.getSession().getAttribute(
					"adm_username");
			academy_id = info_Query.admQuery(adm_username).getAcademy_id();
			//---------后台已动态修改活动状态
			/*
			// 求出活动状态
			long nowTime = new java.util.Date().getTime();// 获取当前时间
			if (startDate.getTime() > nowTime) {
				act_status = 0;//活动未开始
			} else if (startDate.getTime() <= nowTime
					&& nowTime <= endDate.getTime()) {
				act_status = 1;//活动正在进行
			} else {
				act_status = 2;//活动已结束
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return info_Update.addAct(title, content, startDate, endDate,
				academy_id, act_enrollment);
	}
}
 