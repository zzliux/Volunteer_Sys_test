package cn.edu.hnuc.volunteer_Sys.entity;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

/**
 * 活动实体类
 * 
 * @author huhong
 *
 */
public class Activity {

	Integer act_id; // 活动ID
	String act_title; // 活动标题
	String act_content; // 活动内容概要
	Date act_startTime; // 活动起始时间
	Date act_endTime; // 活动结束时间
	Integer academy_id;// 学院代号
	JSONObject academy_info;// 学院信息
	Integer act_status;// 活动状态
	String act_imgurl;// 活动图片地址
	Integer act_enrollment;// 总报名人数
	Integer act_actual_enrollment;// 实际报名人数
	List<Students> stuList; // 参与该活动的学生列表

	public Activity() {
		super();
	}

	public Activity(Integer act_id, String act_title, String act_content,
			Date act_startTime, Date act_endTime, Integer academy_id,
			JSONObject academy_info, Integer act_status, String act_imgurl,
			Integer act_enrollment, Integer act_actual_enrollment,
			List<Students> stuList) {
		super();
		this.act_id = act_id;
		this.act_title = act_title;
		this.act_content = act_content;
		this.act_startTime = act_startTime;
		this.act_endTime = act_endTime;
		this.academy_id = academy_id;
		this.academy_info = academy_info;
		this.act_status = act_status;
		this.act_imgurl = act_imgurl;
		this.act_enrollment = act_enrollment;
		this.act_actual_enrollment = act_actual_enrollment;
		this.stuList = stuList;
	}

	public Integer getAct_id() {
		return act_id;
	}

	public void setAct_id(Integer act_id) {
		this.act_id = act_id;
	}

	public String getAct_title() {
		return act_title;
	}

	public void setAct_title(String act_title) {
		this.act_title = act_title;
	}

	public String getAct_content() {
		return act_content;
	}

	public void setAct_content(String act_content) {
		this.act_content = act_content;
	}

	public Date getAct_startTime() {
		return act_startTime;
	}

	public void setAct_startTime(Date act_startTime) {
		this.act_startTime = act_startTime;
	}

	public Date getAct_endTime() {
		return act_endTime;
	}

	public void setAct_endTime(Date act_endTime) {
		this.act_endTime = act_endTime;
	}

	public Integer getAcademy_id() {
		return academy_id;
	}

	public void setAcademy_id(Integer academy_id) {
		this.academy_id = academy_id;
	}

	public JSONObject getAcademy_info() {
		return academy_info;
	}

	public void setAcademy_info(JSONObject academy_info) {
		this.academy_info = academy_info;
	}

	public Integer getAct_status() {
		return act_status;
	}

	public void setAct_status(Integer act_status) {
		this.act_status = act_status;
	}

	public String getAct_imgurl() {
		return act_imgurl;
	}

	public void setAct_imgurl(String act_imgurl) {
		this.act_imgurl = act_imgurl;
	}

	public List<Students> getStuList() {
		return stuList;
	}

	public void setStuList(List<Students> stuList) {
		this.stuList = stuList;
	}

	public Integer getAct_enrollment() {
		return act_enrollment;
	}

	public void setAct_enrollment(Integer act_enrollment) {
		this.act_enrollment = act_enrollment;
	}

	public Integer getAct_actual_enrollment() {
		return act_actual_enrollment;
	}

	public void setAct_actual_enrollment(Integer act_actual_enrollment) {
		this.act_actual_enrollment = act_actual_enrollment;
	}

	@Override
	public String toString() {
		return "Activity [act_id=" + act_id + ", act_title=" + act_title
				+ ", act_content=" + act_content + ", act_startTime="
				+ act_startTime + ", act_endTime=" + act_endTime
				+ ", academy_id=" + academy_id + ", academy_info="
				+ academy_info + ", act_status=" + act_status + ", act_imgurl="
				+ act_imgurl + ", act_enrollment=" + act_enrollment
				+ ", act_actual_enrollment=" + act_actual_enrollment
				+ ", stuList=" + stuList + "]";
	}

}
