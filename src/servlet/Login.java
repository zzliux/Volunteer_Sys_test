package servlet;
/**
 * 登入
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.hnuc.volunteer_Sys.entity.Admins;
import cn.edu.hnuc.volunteer_Sys.entity.Students;
import cn.edu.hnuc.volunteer_Sys.util.checkLogin;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;
import cn.edu.hnuc.volunteer_Sys.util.userPsw_Check;
import com.google.code.kaptcha.Constants;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        StringBuilder sb = new StringBuilder();
        if (checkLogin.checkL(request, response) == 0) {
            String Userid = null;
            String UserName = null;
            String PassWord = null;
            String CheckCode = null;
            try {
                Userid = request.getParameter("Userid");
                UserName = request.getParameter("UserName");
                PassWord = request.getParameter("PassWord");
                CheckCode = request.getParameter("CheckCode");

            } catch (Exception e) {
                response.sendRedirect("../login.jsp");
            }
            // 获取正确的验证码
            String ccode = (String) request.getSession().getAttribute(
                    Constants.KAPTCHA_SESSION_KEY);
            if (ccode.equals(CheckCode)) {
                if (Userid.equals("2")) {
                    if(userPsw_Check.checkStu_psw(UserName, PassWord)){
                        //记录登入状态
                        request.getSession().setAttribute("stu_account", UserName);
                        //记录用户信息
                        Students student = info_Query.stuAutQuery(UserName);
                        request.getSession().setAttribute("stuinfo", student);
                        sb.append("学生登入成功！");
                    }else{
                        sb.append("账号或密码错误！");
                    }
                } else {
                    if(userPsw_Check.checkAdm_psw(UserName, PassWord)){
                        //记录登入状态
                        request.getSession().setAttribute("adm_username", UserName);
                        //记录用户信息
                        Admins admin = info_Query.admQuery(UserName);
                        request.getSession().setAttribute("adminfo", admin);
                        sb.append("管理员登入成功！");
                    }else{
                        sb.append("账号或密码错误！");
                    }
                }
            } else {
                sb.append("验证码错误！");
            }
        } else {
            sb.append("您已登入，请注销后再登入！");
        }
        PrintWriter pw;
        pw = response.getWriter();
        pw.print(sb.toString());
        pw.close();
    }

}
