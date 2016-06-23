package cn.edu.hnuc.volunteer_Sys.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.DBConn;

public class superadminVerification {
	public static boolean checkSupadmin_psw(String super_username,String super_password ){
		DBConn db = null;
		try {
			db = new DBConn();
			ResultSet rs = db.executeQuery("SELECT * FROM `superadmin` WHERE super_username=?", super_username);
			while (rs.next()) {
				// 验证密码
				super_password = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + super_username + super_password);
				String truePsw = rs.getString(3);
                return super_password.equals(truePsw);
			}
			return false;
		} catch (SQLException e) {
			return false;
		} finally {
			db.close();
		}
	}
}
