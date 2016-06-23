package JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public DBConn() {
		conn = DBUtils.getConnection();
	}

	// 查询
	public ResultSet executeQuery(String strSql, Object... Parameters) {
		try {
			 ps = this.conn.prepareStatement(strSql);
			for (int i = 0; i < Parameters.length; i++) {
				if (Parameters[i].getClass().getName().contains("String")) {
					ps.setString(i + 1, (String) Parameters[i]);
				} else {
					ps.setInt(i + 1, (int) Parameters[i]);
				}
			}
			this.rs = ps.executeQuery();
			return this.rs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// 插入，删除，修改
	public boolean execute(String strSql, Object... Parameters) {
		try {
			ps = this.conn.prepareStatement(strSql);
			for (int i = 0; i < Parameters.length; i++) {
				if (Parameters[i].getClass().getName().contains("String")) {
					ps.setString(i + 1, (String) Parameters[i]);
				} else if(Parameters[i].getClass().getName().contains("java.sql.Date")){
					ps.setDate(i + 1,(Date) Parameters[i]);
				}	else{
					ps.setInt(i + 1, (int) Parameters[i]);
				}
			}
			if (ps.executeUpdate() == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void close() {
		try {
			if (this.rs != null) {
				this.rs.close();
			}
			if (this.ps != null) {
				this.ps.close();
			}
			if (this.conn != null) {
				this.conn.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
