package cn.edu.hnuc.volunteer_Sys.entity;
/**
 * 管理员表(一个学院一个账号)
 */
public class Admins {
    Integer adm_id; // 管理员ID
    String adm_username; // 管理员账号
    String adm_pwd; // 管理员密码
    int academy_id;//管理员所属学院id
    Academy academy_info;//学院信息

    public Admins() {
        super();
    }

    public Admins(Integer adm_id, String adm_username, String adm_pwd,
            int academy_id, Academy academy_info) {
        super();
        this.adm_id = adm_id;
        this.adm_username = adm_username;
        this.adm_pwd = adm_pwd;
        this.academy_id = academy_id;
        this.academy_info = academy_info;
    }

    public Integer getAdm_id() {
        return adm_id;
    }

    public void setAdm_id(Integer adm_id) {
        this.adm_id = adm_id;
    }

    public String getAdm_username() {
        return adm_username;
    }

    public void setAdm_username(String adm_username) {
        this.adm_username = adm_username;
    }

    public String getAdm_pwd() {
        return adm_pwd;
    }

    public void setAdm_pwd(String adm_pwd) {
        this.adm_pwd = adm_pwd;
    }

    public int getAcademy_id() {
        return academy_id;
    }

    public void setAcademy_id(int academy_id) {
        this.academy_id = academy_id;
    }

    public Academy getAcademy_info() {
        return academy_info;
    }

    public void setAcademy_info(Academy academy_info) {
        this.academy_info = academy_info;
    }

    @Override
    public String toString() {
        return "Admins [adm_id=" + adm_id + ", adm_username=" + adm_username + ", adm_pwd=" + adm_pwd + ", academy_id=" + academy_id + ", academy_info=" + academy_info + "]";
    }
}
