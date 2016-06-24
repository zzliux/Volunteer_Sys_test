package servlet;
/*
 * 修改学生基本信息
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

@WebServlet("/Admin_Stus_update")
public class Admin_Stus_update extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Admin_Stus_update() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
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
                updatemsg = "error";
            }
            // 0表示执行删除操作，1表示执行修改操作
            if (action != null) {
                switch (action) {
                    case "0":
                        if(delete(request)){
                            updatemsg = "删除成功！";
                        }else{
                            updatemsg = "删除失败！";
                        }   break;
                    case "1":
                        if(update(request)){
                            updatemsg = "修改成功！";
                        }else{
                            updatemsg = "修改失败！";
                        }   break;
                    default:
                        updatemsg = "error";
                        break;
                }
            } else {
                updatemsg = "error";
            }
        } else {
            updatemsg = "请登入";
        }
        PrintWriter pw;
        pw = response.getWriter();
        pw.print(updatemsg);
        pw.close();
    }

    // 修改
    // ：前端发送数据：stu_id(学生id),stu_account(学号),stu_pwd(密码),stu_name(名字),stu_sex(性别)
    // stu_phone(电话),stu_qq(qq),stu_email(邮箱)
    private boolean update(HttpServletRequest request) {
        int id;
        String stu_account = null;
        String stu_pwd = null;
        String stu_name = null;
        String stu_sex = null;
        String stu_phone = null;
        String stu_qq = null;
        String stu_email = null;
        int academy_id;
        try {
            // 获取基本信息
            id = Integer.parseInt(request.getParameter("stu_id"));
            stu_account = request.getParameter("stu_account");
            stu_pwd = request.getParameter("stu_pwd");
            stu_name = request.getParameter("stu_name");
            stu_sex = request.getParameter("stu_sex");
            stu_phone = request.getParameter("stu_phone");
            stu_qq = request.getParameter("stu_qq");
            stu_email = request.getParameter("stu_email");
            // 获取学院号
            String adm_username = (String) request.getSession().getAttribute("adm_username");
            academy_id = info_Query.admQuery(adm_username).getAcademy_id();
        } catch (Exception e) {
            return false;
        }
        return info_Update.updateStu(id, stu_account, stu_pwd, stu_name,
                stu_sex, stu_phone, stu_qq, stu_email, academy_id);
    }

    // 删除： 前端发送数据：stu_id(活学生id)，action(请求行为)
    private boolean delete(HttpServletRequest request) {
        int id;
        int academy_id;
        try {
            // 获取基本信息
            id = Integer.parseInt(request.getParameter("stu_id"));
            // 获取学院号
            String adm_username = (String) request.getSession().getAttribute("adm_username");
            academy_id = info_Query.admQuery(adm_username).getAcademy_id();
        } catch (Exception e) {
            return false;
        }
        return info_Update.deleteStu(id, academy_id);
    }
}
