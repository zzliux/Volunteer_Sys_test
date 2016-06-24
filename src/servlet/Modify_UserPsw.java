package servlet;
/*
 * 修改所有用户的密码
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.hnuc.volunteer_Sys.util.checkLogin;
import cn.edu.hnuc.volunteer_Sys.util.userPsw_Change;
import cn.edu.hnuc.volunteer_Sys.util.userPsw_Check;

@WebServlet("/Modify_UserPsw")
public class Modify_UserPsw extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Modify_UserPsw() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int userid = checkLogin.checkL(request, response);
        String msg;
        if (userid != 0) {
            // 接收旧密码
            String oldPsw = null;
            // 接收新密码
            String newPsw = null;
            // 接收确认密码
            String ConfirmPwd = null;
            try {
                oldPsw = request.getParameter("oldPsw");
                newPsw = request.getParameter("newPsw");
                ConfirmPwd = request.getParameter("ConfirmPwd");
                switch (userid) {
                    case 1:
                        // 获取学生学号
                        String aut = (String)request.getSession().getAttribute("stu_account");
                        msg = Stu_Modify_Psw(aut, oldPsw, newPsw, ConfirmPwd);
                        break;
                    case 2:
                        // 获取管理员账号
                        String uName = (String)request.getSession().getAttribute("adm_username");
                        msg = Adm_Modify_Psw(uName, oldPsw, newPsw, ConfirmPwd);
                        break;
                    default:
                        // 获取超级管理员账号
                        String superUname = (String)request.getSession().getAttribute("superadmin");
                        msg = Superadm_Modify_Psw(superUname, oldPsw, newPsw,
                                ConfirmPwd);
                        break;
                }
            } catch (Exception e) {
                msg="error！";
            }
        }else{
            msg="请登入！";
        }
        PrintWriter pw;
        pw = response.getWriter();
        pw.print(msg);
        pw.close();
    }

    // 修改学生密码
    private String Stu_Modify_Psw(String aut, String oldPsw, String newPsw, String confirmPwd) {
        // 校验旧密码
        if (!userPsw_Check.checkStu_psw(aut, oldPsw)) {
            return "原始密码错误";
        }
        if(!newPsw.equals(confirmPwd)){
            return "两次输入不一致";
        }
        // 插入新密码
        if(userPsw_Change.stu_Change(aut, newPsw)){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }

    // 修改管理员密码
    private String Adm_Modify_Psw(String uName, String oldPsw, String newPsw, String confirmPwd) {
        // 校验旧密码
        if (!userPsw_Check.checkAdm_psw(uName, oldPsw)) {
            return "原始密码错误";
        }
        if(!newPsw.equals(confirmPwd)){
            return "两次输入不一致";
        }
        // 插入新密码
        if(userPsw_Change.adm_Change(uName, newPsw)){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }

    // 修改超级管理员密码
    private String Superadm_Modify_Psw(String superUname, String oldPsw,
            String newPsw, String confirmPwd) {
        // 校验旧密码
        if (!userPsw_Check.checkSupAdm_psw(superUname, oldPsw)) {
            return "原始密码错误";
        }
        if(!newPsw.equals(confirmPwd)){
            return "两次输入不一致";
        }
        // 插入新密码
        if( userPsw_Change.supdm_Change(superUname, newPsw)){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }
}
