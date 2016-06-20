package cn.edu.hnuc.volunteer_Sys.entity;

import java.util.List;

import org.json.JSONObject;

/**
 * 学生实体类
 * 
 * @author huhong
 *
 */
public class Students {
	Integer stu_id; // 学生ID
	String stu_account; // 学号(账号)
	String stu_pwd; // 密码
	String stu_name; // 学生姓名
	String stu_sex; // 学生性别
	String stu_phone; // 学生电话
	String stu_qq; // 学生QQ
	int academy_id;// 学院代号
	JSONObject academy_info;// 学院信息
	String stu_email;// 邮箱
	List<Activity> actList; // 该名学生所参加的活动

	public Students() {
		super();
	}

	public Students(Integer stu_id, String stu_account, String stu_pwd,
			String stu_name, String stu_sex, String stu_phone, String stu_qq,
			int academy_id, JSONObject academy_info, String stu_email,
			List<Activity> actList) {
		super();
		this.stu_id = stu_id;
		this.stu_account = stu_account;
		this.stu_pwd = stu_pwd;
		this.stu_name = stu_name;
		this.stu_sex = stu_sex;
		this.stu_phone = stu_phone;
		this.stu_qq = stu_qq;
		this.academy_id = academy_id;
		this.academy_info = academy_info;
		this.stu_email = stu_email;
		this.actList = actList;
	}

	public Integer getStu_id() {
		return stu_id;
	}

	public void setStu_id(Integer stu_id) {
		this.stu_id = stu_id;
	}

	public String getStu_account() {
		return stu_account;
	}

	public void setStu_account(String stu_account) {
		this.stu_account = stu_account;
	}

	public String getStu_pwd() {
		return stu_pwd;
	}

	public void setStu_pwd(String stu_pwd) {
		this.stu_pwd = stu_pwd;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_sex() {
		return stu_sex;
	}

	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}

	public String getStu_phone() {
		return stu_phone;
	}

	public void setStu_phone(String stu_phone) {
		this.stu_phone = stu_phone;
	}

	public String getStu_qq() {
		return stu_qq;
	}

	public void setStu_qq(String stu_qq) {
		this.stu_qq = stu_qq;
	}

	public int getAcademy_id() {
		return academy_id;
	}

	public void setAcademy_id(int academy_id) {
		this.academy_id = academy_id;
	}

	public JSONObject getAcademy_info() {
		return academy_info;
	}

	public void setAcademy_info(JSONObject academy_info) {
		this.academy_info = academy_info;
	}

	public String getStu_email() {
		return stu_email;
	}

	public void setStu_email(String stu_email) {
		this.stu_email = stu_email;
	}

	public List<Activity> getActList() {
		return actList;
	}

	public void setActList(List<Activity> actList) {
		this.actList = actList;
	}

	@Override
	public String toString() {
		return "Students [stu_id=" + stu_id + ", stu_account=" + stu_account
				+ ", stu_pwd=" + stu_pwd + ", stu_name=" + stu_name
				+ ", stu_sex=" + stu_sex + ", stu_phone=" + stu_phone
				+ ", stu_qq=" + stu_qq + ", academy_id=" + academy_id
				+ ", academy_info=" + academy_info + ", stu_email=" + stu_email
				+ ", actList=" + actList + "]";
	}

	
}
