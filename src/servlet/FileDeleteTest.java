package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hnuc.volunteer_Sys.util.DeleteFileUtil;

/**
 * Servlet implementation class FileDeleteTest
 */
@WebServlet("/FileDeleteTest")
public class FileDeleteTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileDeleteTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		try {
			String fileName = request.getParameter("fileName");
//			/var/lib/tomcat7/webapps/Volunteer_Sys_test/image/
//			String deletePath = "/var/lib/tomcat7/webapps/Volunteer_Sys_test/image/";
			String deletePath = "D:/temp/";
			if(DeleteFileUtil.deleteFile(deletePath + fileName)){
				sb.append("suceess!");
			}else{
				sb.append("fail!");
			}
	
			
		} catch (Exception e) {
			sb.append("error!");
		}
		PrintWriter pw = response.getWriter();
		pw.print(sb.toString());
		pw.close();
	}

}
