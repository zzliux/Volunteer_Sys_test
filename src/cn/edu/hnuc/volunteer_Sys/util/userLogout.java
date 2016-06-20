package cn.edu.hnuc.volunteer_Sys.util;

import javax.servlet.http.HttpServletRequest;

public class userLogout {
	public static boolean logout(HttpServletRequest request) {
		if(request.getSession().getAttribute("adm_username")!=null){
			request.getSession().removeAttribute("adm_username");
			return true;
		}
		if(request.getSession().getAttribute("stu_account")!=null){
			request.getSession().removeAttribute("stu_account");
			return true;
		}
		if(request.getSession().getAttribute("superadmin")!=null){
			request.getSession().removeAttribute("superadmin");
			return true;
		}
		return false;
	}
}
