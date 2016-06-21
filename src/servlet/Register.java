package servlet;
/*
 * 注册
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.code.kaptcha.Constants;

import JDBC.DBConn;
import cn.edu.hnuc.volunteer_Sys.util.MD5Util;
import cn.edu.hnuc.volunteer_Sys.util.info_Check;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 'UserName=' + username + 'Account=' + account
	// + 'PassWord+' + password + 'Confirm_Password=' + conpsw
	// + "Gender=" + gender + 'Tel=' + tel + 'QQ' + qq + 'Email=' + ema
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		StringBuffer sb = new StringBuffer();

		String UserName = null;
		int Academy = 0;
		String Account = null;
		String PassWord = null;
		String Confirm_Password = null;
		String Gender = null;
		String Tel = null;
		String QQ = null;
		String Email = null;
		String CheckCode = null;
		// Students student;
		try {
			UserName = request.getParameter("UserName");
			Academy = Integer.parseInt(request.getParameter("Academy"));
			Account = request.getParameter("Account");
			PassWord = request.getParameter("PassWord");
			Confirm_Password = request.getParameter("Confirm_Password");
			Gender = request.getParameter("Gender");
			Tel = request.getParameter("Tel");
			QQ = request.getParameter("QQ");
			Email = request.getParameter("Email");
			CheckCode = request.getParameter("CheckCode");
//			 System.out.println(UserName);
			// System.out.println(Academy);
			// System.out.println(Account);
			// System.out.println(PassWord);
			// System.out.println(Confirm_Password);
//			 System.out.println(Gender);
			// System.out.println(Tel);
			// System.out.println(QQ);
			// System.out.println(Email);
			// student = new Students(Account, PassWord, UserName, Gender, Tel,
			// QQ,Academy , Email);

		} catch (Exception e) {
			response.sendRedirect("../register.jsp");
		}
		// 获取正确的验证码
		String ccode = (String) request.getSession().getAttribute(
				Constants.KAPTCHA_SESSION_KEY);
		// System.out.println(ccode+","+CheckCode);
		if (ccode.equals(CheckCode)) {
			String msg = info_Check.regCheck(UserName, Academy, Account, PassWord,
					Confirm_Password, Gender, Tel, QQ, Email);
			if (msg.equals("true")) {
				if (userNotExit(Account)) {
					DBConn db = new DBConn();
					// 密码加密后保存
					PassWord = MD5Util.MD5("Yb6CwCWP2rh1veRyn5SgCC4vHTE5Awlp"
							+ Account + PassWord);
					if (db.execute(
							"INSERT INTO students(stu_account,stu_pwd,stu_name,stu_sex,stu_phone,stu_qq,academy_id,stu_email)values(?,?,?,?,?,?,?,?)",
							Account, PassWord, UserName, Gender, Tel, QQ,
							Academy, Email)) {
						sb.append("注册成功!");
					}
				} else {
					sb.append("对不起，学号已被注册!");
				}
			} else {
				sb.append(msg);
			}
		} else {
			sb.append("验证码错误！");
		}
		PrintWriter pw = response.getWriter();
		pw.print(sb.toString());
		pw.close();
	}

	public boolean userNotExit(String Account) {
		try {
			DBConn db = new DBConn();
			ResultSet rs = db.executeQuery(
					"SELECT * FROM `students` WHERE stu_account=?", Account);
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return false;
		}

	}

}
