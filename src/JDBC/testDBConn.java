package JDBC;


public class testDBConn {

	public static void main(String[] args) {
		/*
		//查询test
		DBConn db = new DBConn();
		ResultSet rs = db.executeQuery("select * from `userdata` where id=?",1);
		try {
			while(rs.next()){
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		*/
		
		/*
		//插入test
		DBConn db = new DBConn();
		System.out.println(db.execute("INSERT INTO userdata(username,score,other)values(?,?,?)","md",1000,".."));
		*/
		
		//删除
		DBConn db = new DBConn();
		System.out.println(db.execute("DELETE FROM userdata WHERE id=?",5));
	}

}
