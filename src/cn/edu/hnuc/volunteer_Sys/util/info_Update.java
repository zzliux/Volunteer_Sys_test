package cn.edu.hnuc.volunteer_Sys.util;

import java.sql.ResultSet;
import java.util.Date;

import JDBC.DBConn;

public class info_Update {
	// ---------------------------------------------活动的增删改-------------------------------------------------------------------------
	// 增加活动
	public static boolean addAct(String act_title, String act_content,
			Date act_startTime, Date act_endTime, int academy_id,
			int act_enrollment) {
		// 求出活动状态
		int act_status = updateActstatus(act_startTime, act_endTime);
		DBConn db = null;
		try {
			db = new DBConn();
            return db.execute("INSERT INTO `activity` (`act_title`, `act_content`, `act_startTime`, `act_endTime`, `academy_id`,`act_status`,`act_enrollment`) VALUES (?,?,?,?,?,?,?)", act_title, act_content, act_startTime, act_endTime, academy_id, act_status, act_enrollment);
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
	}

	// 根据活动id删除活动，且管理员只能删除本院活动
	public static boolean deleteAct(int act_id, int academy_id) {
		try {
			// 创办该活动的学院id
			int aca_id = info_Query.actQuery(act_id).getAcademy_id();
			// 如果不是本院活动，不能删除
			if (aca_id != academy_id) {
				return false;
			}
			DBConn db = null;
			try {
				db = new DBConn();
				//删除关联表中参加该活动的学生
				db.execute("DELETE FROM `act_stu_relation` WHERE (`act_id`=?)",act_id);
                //删除该活动
                return db.execute("DELETE FROM `activity` WHERE (`act_id`=?)",act_id);
			} catch (Exception e) {
				return false;
			} finally {
				db.close();
			}
		} catch (Exception e) {
			return false;
		}
	}

	// 根据活动id修改活动,且管理员只能修改本院活动
	public static boolean updateAct(int act_id, String act_title,
			String act_content, Date act_startTime, Date act_endTime,
			int academy_id, int act_enrollment) {
		int aca_id;
		int act_status;
		try {
			// 创办该活动的学院id
			aca_id = info_Query.actQuery(act_id).getAcademy_id();
			// 如果不是本院活动，不能修改
			if (aca_id != academy_id) {
				return false;
			}
			// 求出活动状态
			act_status = updateActstatus(act_startTime, act_endTime);
		} catch (Exception e) {
			return false;
		}
		DBConn db = null;
		try {
			db = new DBConn();
            return db.execute("UPDATE `activity` SET `act_title`=?, `act_content`=?, `act_startTime`=?, `act_endTime`=?, `academy_id`=?, `act_status`=? ,`act_enrollment`=? WHERE (`act_id`=?)", act_title, act_content, act_startTime, act_endTime, academy_id, act_status, act_enrollment, act_id);
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
	}

	// 根据活动开始结束时间动态修改活动状态
	public static int updateActstatus(Date act_startTime, Date act_endTime) {
		// 求出活动状态 ,0表示未开始，1表示正在进行，2表示已结束
		int act_status;
		long nowTime = new java.util.Date().getTime();// 获取当前时间
		if (act_startTime.getTime() > nowTime) {
			act_status = 0;
		} else if (act_startTime.getTime() <= nowTime
				&& nowTime <= act_endTime.getTime()) {
			act_status = 1;
		} else {
			act_status = 2;
		}
		return act_status;
	}

	// ---------------------------------------------普通管理员的增删改-------------------------------------------------------------------------
	// 增加普通管理员
	public static boolean addAdmin(String adm_username, String adm_pwd, int academy_id) {
		DBConn db = null;
		try {
			// 使用MD将密码加密
			adm_pwd = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp"
					+ adm_username + adm_pwd);
			db = new DBConn();
            return db.execute("INSERT INTO `admins` (`adm_username`, `adm_pwd`, `academy_id`) VALUES (?,?,?)", adm_username, adm_pwd, academy_id);
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
	}

	// 修改普通管理员信息
	public static boolean updateAdmin(int adm_id, String adm_username,
			String adm_pwd, int academy_id) {
		DBConn db = null;
		try {
			// 使用MD将密码加密
			adm_pwd = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + adm_username + adm_pwd);
			db = new DBConn();
            return db.execute("UPDATE `admins` SET `adm_username`=?, `adm_pwd`=?, `academy_id`=? WHERE (`adm_id`=?)",adm_username, adm_pwd, academy_id, adm_id);
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
	}

	// 根据管理员id删除普通管理员
	public static boolean deleteAdmin(int adm_id) {
		DBConn db = null;
		try {
			db = new DBConn();
            return db.execute("DELETE FROM `admins` WHERE (`adm_id`=?)", adm_id);
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
	}

	// -----------------------------学生的删改--------------------------------------
	// 学生信息的修改
	public static boolean updateStu(int id, String stu_account, String stu_pwd,
			String stu_name, String stu_sex, String stu_phone, String stu_qq,
			String stu_email, int academy_id) {
		try {

			// 学生所属学院
			int aca_id = info_Query.stuIdQuery(id).getAcademy_id();
			// 如果不是本院学生，不能修改
			if (aca_id != academy_id) {
				return false;
			}
			DBConn db = null;
			try {
				// 使用MD5将密码加密
				stu_pwd = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp" + stu_account + stu_pwd);
				db = new DBConn();
                return db.execute("UPDATE `students` SET `stu_account`=?, `stu_pwd`=?, `stu_name`=?, `stu_sex`=?,`stu_phone`=?,`stu_qq`=?,`academy_id`=?,`stu_email`=? WHERE (`stu_id`=?)",stu_account, stu_pwd, stu_name, stu_sex, stu_phone,stu_qq, academy_id, stu_email, id);
			} catch (Exception e) {
				return false;
			} finally {
				db.close();
			}
		} catch (Exception e) {
			return false;
		}

	}

	// 学生信息的删除
	public static boolean deleteStu(int id, int academy_id) {
		try {
			// 学生所属学院
			int aca_id = info_Query.stuIdQuery(id).getAcademy_id();
			// 如果不是本院学生，不能删除
			if (aca_id != academy_id) {
				return false;
			}
			DBConn db = null;
			try {
				db = new DBConn();
				// 先删除关联表中的(学生——活动)
				ResultSet rs = db.executeQuery("SELECT * FROM `act_stu_relation` WHERE (`stu_id`=?)",id);
				if (rs != null) {
					db.execute("DELETE FROM `act_stu_relation` WHERE (`stu_id`=?)",id);
					db.execute("DELETE FROM `students` WHERE (`stu_id`=?)", id);
				} else {
					db.execute("DELETE FROM `students` WHERE (`stu_id`=?)", id);
				}
				return true;
			} catch (Exception e) {
				return false;
			} finally {
				db.close();
			}
		} catch (Exception e) {
			return false;
		}
	}

	// -----------------------------学生参加活动的删除--------------------------------------
	public static boolean stu_act_delete(int id, int act_id, int academy_id) {
		try {
			// 创办该活动的学院id
			int aca_id = info_Query.stuIdQuery(id).getAcademy_id();
			// 如果不是本院学生，不能删除
			if (aca_id != academy_id) {
				return false;
			}
			DBConn db = null;
			try {
				db = new DBConn();
                return db.execute("DELETE FROM `act_stu_relation` WHERE (`act_id`=?) AND (`stu_id`=?)",act_id, id);
			} catch (Exception e) {
				return false;
			} finally {
				db.close();
			}
		} catch (Exception e) {
			return false;
		}
	}

}
