package servlet;
/*
 * 上传文件测试
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从request当中获取流信息
		InputStream fileSoure = request.getInputStream();
		String tempFileName = "D:/tempFile";
		//tempFile指向临时文件
		File tempFile = new File(tempFileName);
		//outputStream文件输出流指向这个临时文件
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		byte b[] = new byte[1024];
		int n;
		while((n = fileSoure.read(b))!= -1){
			outputStream.write(b,0,n);
		}
		//关闭输入输出流
		outputStream.close();
		fileSoure.close();
		
		//获取文件名
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");//r表示只读
		randomFile.readLine();
		String str = randomFile.readLine();
		int beginIndex = str.lastIndexOf("\\")+1;
		int endIndex = str.lastIndexOf("\"");
		String filename = str.substring(beginIndex,endIndex);
		System.out.println("filename:"+filename);
		
		//重新定位指针到指针头
		randomFile.seek(0);
		long startPosition = 0;
		int i=1;//读取行数
		//获取文件内容开始位置
		while((n = randomFile.read())!=-1 && i<=4){
			if(n == '\n'){
				startPosition = randomFile.getFilePointer();
				i++;
			}
		}
		startPosition--;
		//获取文件内容结束位置
		randomFile.seek(randomFile.length());
		long endPosition = randomFile.getFilePointer();
		int j=1;
		while(endPosition>=0&&j<=2){
			endPosition--;
			randomFile.seek(endPosition);
			if(randomFile.readByte() == '\n'){
				j++;
			}
		}
		endPosition--;
		
		//设置保存文件的路径
		String realPath = getServletContext().getRealPath("/") + "images";
		File fileupload = new File(realPath);
		if(!fileupload.exists()){
			fileupload.mkdir();
		}
		
		File saveFile = new File(realPath,filename);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "w");
		//从临时文件当中读取文件内容(根据起止位置获取)
		randomFile.seek(startPosition);
		while(startPosition < endPosition){
			randomAccessFile.write(randomFile.readByte());
			startPosition = randomFile.getFilePointer();
		}
		
		//关闭输入输出流、删除临时文件
		randomAccessFile.close();
		randomFile.close();
		tempFile.delete();
		
		
		PrintWriter pw = response.getWriter();
		pw.print("上传成功");
		pw.close();
	}

}
