package servlet;
/*
 * 管理员查看本院学生信息
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
import cn.edu.hnuc.volunteer_Sys.entity.Students;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;

@WebServlet("/Admin_Stus_show")
public class Admin_Stus_show extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Admin_Stus_show() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        StringBuilder sb = new StringBuilder();
        int userid = cn.edu.hnuc.volunteer_Sys.util.checkLogin.checkL(request, response);
        //只能普通管理员查看本院学生信息
        if(userid==2){
            sb.append("[");
            String adm_username = (String) request.getSession().getAttribute("adm_username");// 获取管理员账号
            int academy_id = info_Query.admQuery(adm_username).getAcademy_id();
            ArrayList<Students> students = info_Query.stusQuery(academy_id);
            for (int i = 0; i < students.size(); i++) {
                sb.append(JsonHelper.toJSON(students.get(i)));
                if (i != students.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
        }else{
            sb.append("empty");
        }
        PrintWriter pw;
        pw = response.getWriter();
        pw.print(sb.toString());
        pw.close();
    }
}
