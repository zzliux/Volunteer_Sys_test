package cn.edu.hnuc.volunteer_Sys.util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import JDBC.DBConn;
import JSON.JsonHelper;
import cn.edu.hnuc.volunteer_Sys.entity.Academy;
import cn.edu.hnuc.volunteer_Sys.entity.Activity;
import cn.edu.hnuc.volunteer_Sys.entity.Admins;
import cn.edu.hnuc.volunteer_Sys.entity.Students;
import cn.edu.hnuc.volunteer_Sys.entity.Superadm;

public class info_Query {
    // 根据学院id查询学院信息
    public static Academy acaIdQuery(int aca_id) {
        Academy aca = new Academy();
        DBConn db = null;
        try {
            db = new DBConn();
            ResultSet rs = db.executeQuery("SELECT * FROM `academy` WHERE aca_id=?", aca_id);
            while (rs.next()) {
                aca.setAca_id(aca_id);
                aca.setAca_name(rs.getString(2));
                aca.setAca_man(rs.getString(3));
                aca.setAca_phone(rs.getString(4));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return aca;
    }

    // 根据学生id查询学生基本信息
    public static Students stuIdQuery(int stu_id) {
        Students s = new Students();
        DBConn db = null;
        try {
            db = new DBConn();
            ResultSet rs = db.executeQuery("SELECT * FROM `students` WHERE stu_id=?", stu_id);
            while (rs.next()) {
                s.setStu_id(rs.getInt(1));
                s.setStu_account(rs.getString(2));
                s.setStu_name(rs.getString(4));
                s.setStu_sex(rs.getString(5));
                s.setStu_phone(rs.getString(6));
                s.setStu_qq(rs.getString(7));
                s.setAcademy_id(rs.getInt(8));
                s.setStu_email(rs.getString(9));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return s;
    }

    // 根据学号查询学生基本信息
    @SuppressWarnings("unchecked")
    public static Students stuAutQuery(String Account) {
        Students s = new Students();
        DBConn db = null;
        try {
            db = new DBConn();
            ResultSet rs = db.executeQuery("SELECT * FROM `students` WHERE stu_account=?", Account);
            while (rs.next()) {
                s.setStu_id(rs.getInt(1));
                s.setStu_account(rs.getString(2));
                s.setStu_name(rs.getString(4));
                s.setStu_sex(rs.getString(5));
                s.setStu_phone(rs.getString(6));
                s.setStu_qq(rs.getString(7));
                s.setAcademy_id(rs.getInt(8));
                s.setStu_email(rs.getString(9));
                s.setActList(act_Stu_Query(s));
                s.setAcademy_info(JsonHelper.toJSON(acaIdQuery(s.getAcademy_id())));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return s;
    }

    // 普通管理员查询所有学生，且只能查询本院的学生
    public static ArrayList<Students> stusQuery(int academy_id) {
        ArrayList<Students> students = new ArrayList<Students>();
        DBConn db = null;
        ResultSet rs = null;
        try {
            db = new DBConn();
            rs = db.executeQuery("SELECT * FROM `students`");
            while (rs.next()) {
                Students s = new Students();
                s.setAcademy_id(rs.getInt(8));
                if (s.getAcademy_id() == academy_id) {
                    s.setStu_id(rs.getInt(1));
                    s.setStu_account(rs.getString(2));
                    s.setStu_name(rs.getString(4));
                    s.setStu_sex(rs.getString(5));
                    s.setStu_phone(rs.getString(6));
                    s.setStu_qq(rs.getString(7));
                    s.setStu_email(rs.getString(9));
                    s.setAcademy_info(JsonHelper.toJSON(acaIdQuery(s
                            .getAcademy_id())));
                    students.add(s);
                }
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return students;
    }

    // 根据管理员名称查询管理员基本信息
    public static Admins admQuery(String adm_username) {
        Admins a = null;
        DBConn db = null;
        try {
            db = new DBConn();
            ResultSet rs = db
                    .executeQuery(
                            "SELECT * FROM `admins` WHERE adm_username=?",
                            adm_username);
            while (rs.next()) {
                a = new Admins();
                a.setAdm_id(rs.getInt(1));
                a.setAdm_username(rs.getString(2));
                a.setAcademy_id(rs.getInt(4));
                a.setAcademy_info(acaIdQuery(a.getAcademy_id()));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return a;
    }
    //根据学院号查询管理员基本信息
    public static Admins adm_acaQuery(int academy_id){
        Admins a = null;
        DBConn db = null;
        try {
            db = new DBConn();
            ResultSet rs = db.executeQuery("SELECT * FROM `admins` WHERE academy_id=?", academy_id);
            while (rs.next()) {
                a = new Admins();
                a.setAdm_id(rs.getInt(1));
                a.setAdm_username(rs.getString(2));
                a.setAcademy_id(rs.getInt(4));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return a;
    }
    // 超级管理员查询全部管理员全所有信息
    public static ArrayList<Admins> adminsQuery() {
        ArrayList<Admins> admins = new ArrayList<Admins>();
        DBConn db = null;
        ResultSet rs = null;
        try {
            db = new DBConn();
            rs = db.executeQuery("SELECT * FROM `admins`");
            while (rs.next()) {
                Admins adm = new Admins();
                adm.setAdm_id(rs.getInt(1));
                adm.setAdm_username(rs.getString(2));
                adm.setAcademy_id(rs.getInt(4));
                adm.setAcademy_info(acaIdQuery(adm.getAcademy_id()));
                admins.add(adm);
            }
        } catch (SQLException e) {
        } finally {
            db.close();
        }
        return admins;
    }

    // 根据活动id查询该活动的报名人数
    public static int enrollmentQuery(int act_id) {
        int enrollment = 0;
        DBConn db = null;
        try {
            db = new DBConn();
            ResultSet rs = db.executeQuery(
                    "SELECT * FROM `act_stu_relation` WHERE act_id=?", act_id);
            while (rs.next()) {
                enrollment++;
            }
        } catch (SQLException e) {
            return 0;
        } finally {
            db.close();
        }
        return enrollment;
    }

    // 根据活动id查询活动
    public static Activity actQuery(int act_id) {
        Activity act = null;
        DBConn db = null;
        try {
            db = new DBConn();
            ResultSet rs = db.executeQuery(
                    "SELECT * FROM `activity` WHERE act_id=?", act_id);
            while (rs.next()) {
                act = new Activity();
                act.setAct_id(rs.getInt(1));
                act.setAct_title(rs.getString(2));
                act.setAct_content(rs.getString(3));
                act.setAct_startTime(rs.getDate(4));
                act.setAct_endTime(rs.getDate(5));
                act.setAcademy_id(rs.getInt(6));// 学院id
                act.setAct_status(info_Update.updateActstatus(act.getAct_startTime(), act.getAct_endTime()));// 活动状态
                act.setAct_enrollment(rs.getInt(9));// 该活动报名人数上限
                act.setAct_actual_enrollment(enrollmentQuery(act_id));// 该活动已经报名人数
                act.setAcademy_info(JsonHelper.toJSON(acaIdQuery(act.getAcademy_id())));// 学院信息
                act.setAct_imgurl(rs.getString(8));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return act;
    }

        //查询所有活动,首页展示
        public static ArrayList<Activity> actsQuery() {
            ArrayList<Activity> acts = new ArrayList<Activity>();
            DBConn db = null;
            ResultSet rs = null;
            try {
                db = new DBConn();
                rs = db.executeQuery("SELECT * FROM `activity`");
                while (rs.next()){
                    Activity act = new Activity();
                    act.setAct_id(rs.getInt(1));
                    act.setAct_title(rs.getString(2));
                    act.setAct_content(rs.getString(3));
                    act.setAct_startTime(rs.getDate(4));
                    act.setAct_endTime(rs.getDate(5));
                    act.setAct_status(info_Update.updateActstatus(act.getAct_startTime(), act.getAct_endTime()));// 活动状态
                    act.setAct_imgurl(rs.getString(8));
                    act.setAct_enrollment(rs.getInt(9));// 该活动报名人数上限
                    act.setStuList(act_Stu_Query(act));// 报名的学生
                    act.setAct_actual_enrollment(act.getStuList().size());// 报名人数
                    acts.add(act);
                }
            }catch(SQLException e){
                return null;
            } finally {
                db.close();
            }

            return acts;
        }

    // 普通管理员查询所有活动，且只能查询本院的活动
    @SuppressWarnings("unchecked")
    public static ArrayList<Activity> actsQuery(int academy_id) {
        ArrayList<Activity> activities = new ArrayList<Activity>();
        DBConn db = null;
        ResultSet rs = null;
        try {
            db = new DBConn();
            rs = db.executeQuery("SELECT * FROM `activity`");
            while (rs.next()) {
                Activity act = new Activity();
                act.setAcademy_id(rs.getInt(6));
                if (act.getAcademy_id() == academy_id) {
                    act.setAct_id(rs.getInt(1));
                    act.setAct_title(rs.getString(2));
                    act.setAct_content(rs.getString(3));
                    act.setAct_startTime(rs.getDate(4));
                    act.setAct_endTime(rs.getDate(5));
                    act.setAct_status(info_Update.updateActstatus(act.getAct_startTime(), act.getAct_endTime()));// 活动状态
                    act.setAct_enrollment(rs.getInt(9));// 该活动报名人数上限
                    act.setStuList(act_Stu_Query(act));// 报名的学生
                    act.setAct_actual_enrollment(act.getStuList().size());// 报名人数
                    act.setAcademy_info(JsonHelper.toJSON(acaIdQuery(act.getAcademy_id())));// 学院信息
                    activities.add(act);
                }
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return activities;
    }

    // 关联查询 学生——活动
    @SuppressWarnings("rawtypes")
    public static List act_Stu_Query(Object obj) {
        // 查询学生报名的活动以及查询报名该活动的学生
        if (obj.getClass().getName().equals("cn.edu.hnuc.volunteer_Sys.entity.Students")) {
            ArrayList<Activity> activities = new ArrayList<Activity>();
            Students s = (Students) obj;
            DBConn db = null;
            int stu_id = s.getStu_id();
            try {
                db = new DBConn();
                ResultSet rs = db.executeQuery("SELECT * FROM `act_stu_relation` WHERE stu_id=?",stu_id);
                while (rs.next()) {
                    activities.add(actQuery(rs.getInt(1)));
                }
            } catch (SQLException e) {
                return null;
            } finally {
                db.close();
            }
            return activities;
        } else if (obj.getClass().getName().equals("cn.edu.hnuc.volunteer_Sys.entity.Activity")) {
            ArrayList<Students> students = new ArrayList<Students>();
            Activity act = (Activity) obj;
            int act_id = act.getAct_id();
            DBConn db = null;
            try {
                db = new DBConn();
                ResultSet rs = db.executeQuery("SELECT * FROM `act_stu_relation` WHERE act_id=?",act_id);
                while (rs.next()) {
                    students.add(stuIdQuery(rs.getInt(2)));
                }
            } catch (SQLException e) {
                return null;
            } finally {
                db.close();
            }
            return students;
        }
        return null;
    }

    //查询超级管理员信息
    public static Superadm supAdmQuery(String superUname) {
        Superadm sup = new Superadm();
        DBConn db = null;
        try {
            db = new DBConn();
            ResultSet rs = db.executeQuery("SELECT * FROM `superadmin` WHERE super_username=?",superUname);
            while (rs.next()) {
                sup.setSup_id(rs.getInt(1));
                sup.setSup_name(rs.getString(2));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.close();
        }
        return sup;
    }
}
