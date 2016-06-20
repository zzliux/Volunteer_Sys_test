package servlet;
/*
 * 向全体用户展示全部信息
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JSON.JsonHelper;
import cn.edu.hnuc.volunteer_Sys.entity.Activity;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;

@WebServlet("/BasicAct_show")
public class BasicAct_show extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BasicAct_show() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		StringBuffer sb = new StringBuffer();
		int act_id;
		try {
			act_id = Integer.parseInt(request.getParameter("act_id"));
			Activity act = info_Query.actQuery(act_id);
			if(act!=null){
				sb.append(JsonHelper.toJSON(act));
			}else{
				sb.append("error");
			}
		} catch (Exception e) {
//			e.printStackTrace();
//			response.sendRedirect("../error.html");
			sb.append("error");
		}
		PrintWriter pw = response.getWriter();
		pw.print(sb.toString());
		pw.close();
	}
}
