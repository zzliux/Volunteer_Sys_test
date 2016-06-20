package cn.edu.hnuc.volunteer_Sys.util;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.hnuc.volunteer_Sys.entity.Activity;
import cn.edu.hnuc.volunteer_Sys.entity.Admins;
import JDBC.DBConn;
import JSON.JsonHelper;

public class test {

	public static void main(String[] args) {
		// String test =
		// "Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp"+"140920045"+"123456";
		// System.out.println(MD5Util.MD5(test));
		// -----------------------------------------------------------------------
		// ArrayList<Activity> activities = info_Query.ActsQuery();
		// for(Activity act:activities){
		// System.out.println(act.toString());
		// }
		// -----------------------------------------------------------------------
		// Students s = info_Query.StuActQuery("140920045");
		// System.out.println(s.toString());
		// -----------------------------------------------------------------------
		// List list = new ArrayList<Activity>();
		// Activity act = new Activity();
		// Students stu = new Students();
		// list.add(act);
		// list.add(stu);
		// System.out.println(list.get(0).getClass().getName());
		// System.out.println(list.get(1).getClass().getName());
		// System.out.println(list.size());
		// -----------------------------------------------------------------------
		// //测试查询所有活动
		// ArrayList<Activity> activities = info_Query.ActsQuery();
		// StringBuffer sb = new StringBuffer("[");
		// //-----------------------------------------------------------------------
		// for(int i=0;i<activities.size();i++){
		// System.out.println(activities.get(i));
		// sb.append(JsonHelper.toJSON(activities.get(i)));
		// if(i != activities.size()-1){
		// sb.append(",");
		// }
		// }
		// sb.append("]");
		// System.out.println(sb);
		// -----------------------------------------------------------------------
		// //测试加入活动
		// java.util.Date d = new java.util.Date();
		// System.out.println(info_Update.addAct("test3", "无", new
		// Date(d.getTime()),
		// new Date(d.getTime()), 3,1,30));
		// -----------------------------------------------------------------------
		// 测试删除活动
		// System.out.println(info_Update.deleteAct(3,3));
		// -----------------------------------------------------------------------
		// 测试修改活动
		// java.util.Date d = new java.util.Date();
		// // System.out.println(info_Update
		// // .updateAct(2, "test5", "hehe", d, d, 2, 2));
		// System.out.println(info_Update.updateAct(2, "test2", "HAHA",
		// new Date(d.getTime()), new Date(d.getTime()), 2, 2));
		// -----------------------------------------------------------------------
		// date 测试
		// int act_status;
		//
		// long nowTime = new java.util.Date().getTime();// 获取当前时间
		// Date startDate = new Date(nowTime);
		// Date endDate = new Date(nowTime + 3600 * 24 * 1000);
		// if (startDate.getTime() > nowTime) {
		// act_status = 0;
		// } else if (startDate.getTime() <= nowTime
		// && nowTime <= endDate.getTime()) {
		// act_status = 1;
		// } else {
		// act_status = 2;
		// }
		// System.out.println(nowTime);
		// System.out.println(startDate);
		// System.out.println(endDate);
		// System.out.println(act_status);
		// -----------------------------------------------------------------------

		// 测试查询活动报名人数
		// System.out.println(info_Query.enrollmentQuery(1));
		// -----------------------------------------------------------------------

		// 超级管理员获取所有管理员测试
		// ArrayList<Admins> admins = info_Query.AdminsQuery();
		// for (Admins adm : admins) {
		// System.out.println(adm);
		// }
		// -----------------------------------------------------------------------
		// 增加修改删除管理员测试
		// System.out.println(info_Update.addAdmin("140920048", "123456", 3));
		// System.out.println(info_Update.updateAdmin(1,"140920045",
		// "123456",3));
		// System.out.println(info_Update.deleteAdmin(3));
		// -----------------------------------------------------------------------
		// 修改密码测试
		// System.out.println(userPsw_Change.stu_Change("140920046",
		// "123456"));// 学生
		// System.out.println(userPsw_Change.adm_Change("140920049",
		// "123456"));//管理员
		// System.out.println(userPsw_Change.supdm_Change("140920045",
		// "123456"));//超级管理员
		// -----------------------------------------------------------------------
		// 管理员查询本院学生测试
		// ArrayList<Students> students = info_Query.stusQuery(1);
		// for (Students s : students) {
		// System.out.println(s.toString());
		// }

		// -----------------------------------------------------------------------
		// 删除学生
		// System.out.println(info_Update.deleteStu(2, 2));

		// -----------------------------------------------------------------------
		// 日期测试
		// // Date date = Date.valueOf("2016-03-30");
		// long nowTime = new java.util.Date().getTime();// 获取当前时间
		// Date date = new Date(nowTime);
		// System.out.println(date.toString());

//		DBConn db = new DBConn();
//		// 先删除关联表中的(学生——活动)
//		ResultSet rs = db.executeQuery(
//				"SELECT *  FROM `act_stu_relation` WHERE `stu_id`=?", 1);
//		try {
//			while(rs.next()){
//				System.out.println("test");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(rs==null);
		
		
		
//		System.out.println(info_Query.admQuery("140920041"));

		
//		Activity act = info_Query.actQuery(1);
//		System.out.println(JsonHelper.toJSON(act));
		
//		Admins admin = info_Query.admQuery("140920045");
//		System.out.println(admin.toString());
		
//		System.out.println(	info_Update.stu_act_delete(7, 3, 1));
//		System.out.println(info_Update.deleteAct(7, 1));
	
//		String imgUrl = "1-1,1-2,";
//		System.out.println(imgUrl.split(",").length+1);
		
		
//		System.out.println(act_imgurl_Update.add(2, 1));
		
//		System.out.println(JsonHelper.toJSON(info_Query.actQuery(2)));
		
		
//		String suffix = "1.jpg".split("[.]")[1];
//		System.out.println(suffix);
		
////		String imgUrl = "1-1.jpg,1-2.jpg,1-3.jpg,";
//		String imgUrl = "";
//		String fileName = "1-3.jpg";
//		imgUrl = imgUrl.replace(fileName+",", "");
//		System.out.println(imgUrl);
		
//		System.out.println(act_imgurl_Update.delete(2, 1, "2-1.jpg"));
		
	}
}
