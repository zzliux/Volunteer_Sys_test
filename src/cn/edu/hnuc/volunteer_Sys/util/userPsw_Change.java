package cn.edu.hnuc.volunteer_Sys.util;

import JDBC.DBConn;

/*
 * 修改用户密码工具
 */
public class userPsw_Change {
	//修改学生密码
	public static boolean stu_Change(String aut, String newPsw) {
		DBConn db = null;
		// 对新密码加密
		newPsw = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + aut + newPsw);
		try {
			db = new DBConn();
            return db.execute("UPDATE `students` SET `stu_pwd`=? WHERE (`stu_account`=?)", newPsw, aut);
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
	}
	//修改普通管理员密码
	public static boolean adm_Change(String uName, String newPsw){
		DBConn db = null;
		// 对新密码加密
		newPsw = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + uName + newPsw);
		try {
			db = new DBConn();
            return db.execute( "UPDATE `admins` SET `adm_pwd`=? WHERE (`adm_username`=?)", newPsw, uName);
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
	}
	//修改超级管理员密码
	public static boolean supdm_Change(String uName, String newPsw){
		DBConn db = null;
		// 对新密码加密
		newPsw = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + uName + newPsw);
		try {
			db = new DBConn();
            return db.execute("UPDATE `superadmin` SET `super_pwd`=? WHERE (`super_username`=?)",newPsw, uName);
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
	}
}
