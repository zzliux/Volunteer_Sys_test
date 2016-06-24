package cn.edu.hnuc.volunteer_Sys.util;
import java.sql.ResultSet;
import cn.edu.hnuc.volunteer_Sys.entity.Activity;
import JDBC.DBConn;

public class act_enlist {
    //0表示报名失败，1表示报名成功，2表示已报名,3表示报名人数已满
    public static int enlist(int stu_id, int act_id) {
        //查询该活动是否已经报满
        Activity act = info_Query.actQuery(act_id);
        //如果实际人数等于报名人数，
        if(act.getAct_actual_enrollment() == act.getAct_enrollment()){
            return 3;
        }
        DBConn db = null;
        try {
            //查询该学生报名的活动
            db = new DBConn();
            ResultSet rs = db.executeQuery("SELECT * FROM `act_stu_relation` WHERE stu_id=?", stu_id);
            while (rs.next()) {
                //检测是否已经报名
                if(rs.getInt(1)==act_id){
                    return 2;
                }
            }
            //执行报名
            if (db.execute(
                    "INSERT INTO `act_stu_relation` (`act_id`, `stu_id`) VALUES (?, ?)",
                    act_id, stu_id)) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        } finally {
            db.close();
        }
    }
}
