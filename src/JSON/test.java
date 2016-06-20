package JSON;

import java.text.ParseException;

import org.json.JSONException;

import cn.edu.hnuc.volunteer_Sys.entity.Academy;
import cn.edu.hnuc.volunteer_Sys.entity.Students;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;



public class test {

	public static void main(String[] args) {
//		Students s = new Students(1, "140920045", "123456", "xxmodd", "男", "15111028907", "1300501567", 1, "1300501567@qq.com");
//		JSONObject j =  JsonHelper.toJSON(s);
//		System.out.println(j.toString());
		//测试不成功
//		Students s = info_Query.stuAutQuery("140920045");
//		System.out.println(s.toString());
//		System.out.println(JsonUtil.object2Json(s));
		
//		try {
//			Academy a = new Academy();
//			JsonHelper.toJavaBean(a,"{\"aca_name\":\"计算机与信息工程学院\",\"aca_man\":\"admin\",\"aca_id\":\"1\",\"aca_phone\":\"111\"}");
//			System.out.println(a);
//		} catch (ParseException | JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Students s = info_Query.stuAutQuery("140920045");
		System.out.println(s);
	}
}
