package servlet;
/*
 * 超级管理员后台登入
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.hnuc.volunteer_Sys.util.checkLogin;
import cn.edu.hnuc.volunteer_Sys.util.userPsw_Check;
import com.google.code.kaptcha.Constants;

@WebServlet("/SupadmLogin")
public class SupadmLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SupadmLogin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        StringBuilder sb = new StringBuilder();
        if (checkLogin.checkL(request, response) == 0) {
            String UserName = null;
            String PassWord = null;
            String CheckCode = null;
            // Students student;
            try {
                UserName = request.getParameter("UserName");
                PassWord = request.getParameter("PassWord");
                CheckCode = request.getParameter("CheckCode");

            } catch (Exception e) {
                response.sendRedirect("../superadmin/login.jsp");
            }
            // 获取正确的验证码
            String ccode = (String) request.getSession().getAttribute(
                    Constants.KAPTCHA_SESSION_KEY);
            if (ccode.equals(CheckCode)) {
                if (userPsw_Check.checkSupAdm_psw(UserName, PassWord)) {
                    sb.append("超级管理员登入成功！");
                    request.getSession().setAttribute("superadmin", UserName);
                } else {
                    sb.append("登入失败！");
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
