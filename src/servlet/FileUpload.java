package servlet;

/*
 * 文件传输
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.edu.hnuc.volunteer_Sys.util.act_imgurl_Update;
import cn.edu.hnuc.volunteer_Sys.util.checkLogin;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;

@SuppressWarnings("serial")
public class FileUpload extends HttpServlet {
	// String realpath =
	// request.getSession().getServletContext().getRealPath("/");

//	private static String uploadPath = "D:\\temp\\"; // 上传文件的本地测试目录
//	private static String tempPath = "D:\\temp\\buffer\\"; // 临时本地文件目录
	private static String uploadPath ="/var/lib/tomcat7/webapps/Volunteer_Sys_test/image/"; // 上传文件的服务器目录
	private static String tempPath ="/var/lib/tomcat7/webapps/Volunteer_Sys_test/image/buffer/"; // 临时本服务器文件目录 
	File tempPathFile;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// System.out.println(request.getParameter("id"));
		StringBuffer sb = new StringBuffer();
		int userid = checkLogin.checkL(request, response);
		// 普通管理员才能上传图片
		if (userid == 2) {
			
			try {
				// 获取管理员信息
				String adm_username = (String) request.getSession().getAttribute("adm_username");
				int adm_aca_id = info_Query.admQuery(adm_username).getAcademy_id();
				
				int act_id = -1;
				//String fileName = null;
				FileItem fileItem = null;
				DiskFileItemFactory factory = new DiskFileItemFactory();

				factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
				factory.setRepository(tempPathFile);// 设置缓冲区目录

				ServletFileUpload upload = new ServletFileUpload(factory);

				upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

				List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
				Iterator<FileItem> i = items.iterator();
				// 存储表单数据
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (fi.isFormField()) {
						act_id = Integer.parseInt(fi.getString("utf-8"));// 获取活动id
//						System.out.println("act_id="+act_id);
					}
					if (fi.getName() != null) {
						fileItem = fi;
						//fileName = fi.getName();
//						//上传文件
//						File fullFile = new File(fi.getName());
//						File savedFile = new File(uploadPath,
//								fullFile.getName());
//						fi.write(savedFile);
					}
				}
				//上传图片
				if(upFile(act_id,fileItem,adm_aca_id)){
					sb.append("upload succeed!");
				}else{
					sb.append("upload failed!");
				}
			} catch (Exception e) {
				sb.append("upload failed!");
				// 可以跳转出错页面
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("../login.jsp");
			sb.append("upload failed!");
		}
		PrintWriter pw = response.getWriter();
		pw.print(sb.toString());
		pw.close();
	}
	//上传图片
	public static boolean upFile(int act_id,FileItem fileItem,int academy_id) {
		try {
			//获取源文件的后缀
			String suffix = fileItem.getName().split("[.]")[1];
			int index = act_imgurl_Update.add(act_id, academy_id,suffix);
			if(index==0){
				return false;
			}else{
				//构建文件名
				String imgName = String.valueOf(act_id)+"-"+String.valueOf(index)+"."+suffix;
				File savedFile = new File(uploadPath,imgName);
				fileItem.write(savedFile);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public void init() throws ServletException {
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(tempPath);
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
	}
}