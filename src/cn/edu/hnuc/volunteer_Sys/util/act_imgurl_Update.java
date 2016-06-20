package cn.edu.hnuc.volunteer_Sys.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.DBConn;

/*
 * 活动图片的增删工具
 */
public class act_imgurl_Update {
	//根据活动id查询该活动图片地址信息
	public static String imgUrl_Query(int act_id){
		DBConn db = null;
		try {
			db = new DBConn();
			ResultSet rs = db.executeQuery(
					"SELECT * FROM `activity` WHERE act_id=?", act_id);
			while (rs.next()) {
				return rs.getString(8);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			db.close();
		}
	}
	//添加图片地址到数据库中
	public static int add(int act_id,int adm_aca_id,String suffix){
		int index = 0;
		if(!check(act_id, adm_aca_id)){
			return 0;
		}
		//获取活动图片地址信息
		String imgUrl = imgUrl_Query(act_id);
//		System.out.println(imgUrl); 
		if(imgUrl.equals("")){
			index = 1;
			//更新imgUrl
			imgUrl = String.valueOf(act_id)+"-"+String.valueOf(index)+"."+suffix+",";
//			System.out.println("newimgUrl1="+imgUrl);
		}else{
			index = imgUrl.split(",").length+1;
			//更新imgUrl
			imgUrl = imgUrl+String.valueOf(act_id)+"-"+String.valueOf(index)+"."+suffix+",";
//			 System.out.println("newimgUrl2="+imgUrl);
		}
		DBConn db = null;
		try {
			db = new DBConn();
			if (!db.execute("UPDATE `activity` SET `act_imgurl`=? WHERE (`act_id`=?)",imgUrl,act_id)){
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			db.close();
		}
		return index;
	}
	
	//删除某个图片地址
	public static boolean delete(int act_id,int adm_aca_id,String fileName){
//		System.out.println("act_id="+act_id+" fileName="+fileName);
		if(!check(act_id, adm_aca_id)){
			return false;
		}
		//请求删除的文件名不能为空
		if(fileName.equals("")){
			return false;
		}
		//获取活动图片地址信息
		String imgUrl = imgUrl_Query(act_id);
		//更新imgUrl
		String newimgUrl = imgUrl.replace(fileName+",", "");
		
		//如果该图片地址不存在
		if(imgUrl.equals(newimgUrl)){
			return false;
		}
		
		//更新数据库
		DBConn db = null;
		try {
			db = new DBConn();
			if (!db.execute("UPDATE `activity` SET `act_imgurl`=? WHERE (`act_id`=?)",newimgUrl,act_id)){
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.close();
		}
	}
	
	//判断该管理员是否能管理该活动
	public static boolean check(int act_id,int academy_id){
		int aca_id;
		try {
			// 创办该活动的学院id
			aca_id = info_Query.actQuery(act_id).getAcademy_id();
			// 如果不是本院活动，不能修改
			if (aca_id != academy_id) {
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
