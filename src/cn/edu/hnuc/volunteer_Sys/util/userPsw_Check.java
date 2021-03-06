package cn.edu.hnuc.volunteer_Sys.util;
import java.sql.ResultSet;
import JDBC.DBConn;
/*
 * 用户密码修改
 */
public class userPsw_Check {
    public static boolean checkAdm_psw(String UserName, String PassWord) {
        boolean msg = true;
        try {
            DBConn db = new DBConn();
            ResultSet rs = db.executeQuery("SELECT * FROM `admins` WHERE adm_username=?", UserName);
            if (rs.next()) {
                // 验证密码
                PassWord = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + UserName + PassWord);
                String truePsw = rs.getString(3);
                if (!PassWord.equals(truePsw))
                    msg = false;
            } else {
                msg = false;
            }
        } catch (Exception e) {
            msg = false;
        }
        return msg;
    }

    public static boolean checkStu_psw(String Account, String PassWord) {
        boolean msg = true;
        try {
            DBConn db = new DBConn();
            ResultSet rs = db.executeQuery( "SELECT * FROM `students` WHERE stu_account=?", Account);
            if (rs.next()) {
                // 验证密码
                PassWord = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + Account + PassWord);
                String truePsw = rs.getString(3);
                if (!PassWord.equals(truePsw))
                    msg = false;
            } else {
                msg = false;
            }
        } catch (Exception e) {
            msg = false;
        }
        return msg;
    }

    public static boolean checkSupAdm_psw(String UserName, String PassWord) {
        boolean msg = true;
        try {
            DBConn db = new DBConn();
            ResultSet rs = db.executeQuery("SELECT * FROM `superadmin` WHERE super_username=?", UserName);
            if (rs.next()) {
                // 验证密码
                PassWord = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + UserName + PassWord);
                String truePsw = rs.getString(3);
                if (!PassWord.equals(truePsw)) 
                    msg = false;
            } else {
                msg = false;
            }
        } catch (Exception e) {
            msg = false;
        }
        return msg;
    }
}
