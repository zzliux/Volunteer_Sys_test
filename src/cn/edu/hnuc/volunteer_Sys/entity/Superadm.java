package cn.edu.hnuc.volunteer_Sys.entity;

public class Superadm {
	private String sup_name;
	private Integer sup_id;
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public Integer getSup_id() {
		return sup_id;
	}
	public void setSup_id(Integer sup_id) {
		this.sup_id = sup_id;
	}
	@Override
	public String toString() {
		return "Superadm [sup_name=" + sup_name + ", sup_id=" + sup_id + "]";
	}
	
}
