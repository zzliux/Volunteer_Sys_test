package cn.edu.hnuc.volunteer_Sys.util;
/*
 * 检测用户是否已登入
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class checkLogin {
    public  static int checkL(HttpServletRequest request, HttpServletResponse response){
        //0表示为登入，1表示学生登入，2表示管理员登入,3表示超级管理员登入
        if(request.getSession().getAttribute("stu_account")!=null){
            return 1;
        }else if(request.getSession().getAttribute("adm_username")!=null){
            return 2;
        }else if(request.getSession().getAttribute("superadmin")!=null){
            return 3;
        }
        return 0;
    }
}
