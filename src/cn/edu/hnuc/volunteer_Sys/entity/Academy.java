package cn.edu.hnuc.volunteer_Sys.entity;

/**
 * 学院实体类
 * @author huhong
 *
 */
public class Academy {
	Integer aca_id; // 学院id
	String aca_name; // 学院名称
	String aca_man; // 学院负责人
	String aca_phone; // 负责人电话
	public Academy() {
		super();
	}

	public Academy(Integer aca_id, String aca_name, String aca_man,
			String aca_phone) {
		super();
		this.aca_id = aca_id;
		this.aca_name = aca_name;
		this.aca_man = aca_man;
		this.aca_phone = aca_phone;
	}
	
	public Integer getAca_id() {
		return aca_id;
	}
	public void setAca_id(Integer aca_id) {
		this.aca_id = aca_id;
	}
	public String getAca_name() {
		return aca_name;
	}
	public void setAca_name(String aca_name) {
		this.aca_name = aca_name;
	}
	public String getAca_man() {
		return aca_man;
	}
	public void setAca_man(String aca_man) {
		this.aca_man = aca_man;
	}
	public String getAca_phone() {
		return aca_phone;
	}
	public void setAca_phone(String aca_phone) {
		this.aca_phone = aca_phone;
	}
	
	
	@Override
	public String toString() {
		return "Academy [aca_id=" + aca_id + ", aca_name=" + aca_name
				+ ", aca_man=" + aca_man + ", aca_phone=" + aca_phone + "]";
	}
	
	
}
