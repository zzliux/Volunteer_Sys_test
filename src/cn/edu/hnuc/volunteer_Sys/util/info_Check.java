package cn.edu.hnuc.volunteer_Sys.util;

/**
 * 服务器表单校验
 * 
 * @author huhong
 *
 */
public class info_Check {
	private static String regex = null;

	// 调用方法check表单信息

	// 注册信息校验
	public static String regCheck(String UserName, int Academy,
			String Account, String PassWord, String Confirm_Password,
			String Gender, String Tel, String QQ, String Email) {

		// 层层检验
		if (!checkAcademy(Academy)) {
			return("学院编号错误！");
		} else if (!checkEmail(Email)) {
			return("邮箱格式错误！");
		} else if (!checkQQ(QQ)) {
			return("QQ号码不合法！");
		} else if (!checkTel(Tel)) {
			return("电话号码不合法！");
		} else if (!checkGender(Gender)) {
			return("你是火星人吗？,请输入性别！");
		} else if (!checkPwd(PassWord)) {
			return("密码必须在6-15个字符！");
		} else if (!verifyPwd(PassWord, Confirm_Password)) {
			return("两次密码输入不一致！");
		} else if (!checkAct(Account)) {
			return("学号填写错误！");
		} else if (!checkUser(UserName)) {
			return("姓名是2-4个汉字！");
		}
		return "true";
	}
	
	public static boolean modify_Psw_Check(String newPsw,String confirmPwd){
		if(!checkPwd(newPsw)){
			return false;
		}else if(!verifyPwd(newPsw,confirmPwd)){
			return false;
		}
		return true;
	}
	// 1 ~ 14 (下拉列表)
	private static boolean checkAcademy(int Academy) {
        return Academy > 0 && Academy < 15;
	}

	// ~~
	private static boolean checkEmail(String email) {

		regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		return email.matches(regex);
	}

	// 10000开始
	private static boolean checkQQ(String qq) {

		regex = "^[1-9][0-9]{4,}$";
		return qq.matches(regex);
	}

	// 11位数字
	private static boolean checkTel(String tel) {
		regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		return tel.matches(regex);
	}

	// 男 | 女
	private static boolean checkGender(String gender) {
		// 去掉全部空格
		gender = gender.replaceAll("\\s*", "");
        return gender.equals("男") || gender.equals("女");
	}

	// 检查重复密码是否输入错误
	private static boolean verifyPwd(String passWord, String confirm_Password) {
		try {
            return passWord.equals(confirm_Password);
		} catch (Exception e) {
			return false;
		}
	}

	// 密码限制(6-15位字符)
	private static boolean checkPwd(String passWord) {
		
		regex = "^.{6,15}$";
		return passWord.matches(regex);

	}

	// 账号(1开头的九位数字)
	private static boolean checkAct(String Account) {

		regex = "^[1]\\d{8}$";
		return Account.matches(regex);
	}

	// 名字限制(2-4个汉字)
	private static boolean checkUser(String userName) {

		regex = "^[\u4e00-\u9fa5]{2,4}$";
		return userName.matches(regex);

	}
}
