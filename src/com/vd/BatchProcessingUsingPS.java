package com.vd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchProcessingUsingPS {
	// Batch Processing Updates

	// Added for password Check
	public static final String driverClass = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sys";
	public static final String username = "root";
	public static final String password = "root";
	///sjfg
	
	//Testing

	// Adding 
	//public static final String sql1 = "insert into Bank_Account_tbl values(?,?,?)";
	//public static final String sql2 = "insert into Bank_Account_tbl values(?,?,?)";
	//public static final String sql3 = "update Bank_Account_tbl set accountHolderName=? where accountNo=?";

	public static void main(String[] args) {
		
		

		Connection con = null;
		PreparedStatement ps= null;

		try {
			

			// load and register driver
			Class.forName(driverClass);

			// create connefction
			con = DriverManager.getConnection(url, username, password);
			
			// Disable auto commit mode on DB
			con.setAutoCommit(false);
			
			
			ps = con.prepareStatement("insert into Bank_Account_tbl values(?,?,?)");
			
			ps.setInt(1, 11111);
			ps.setString(2, "bharat");
			ps.setInt(3, 10000);
			ps.addBatch();
			//Hi
			ps.setInt(1, 11112);
			ps.setString(2, "rameshss");
			ps.setInt(3, 20000);
			ps.addBatch();
			// Batch is executed
			
			
			// executing the bath of queries
			ps.executeBatch();
			con.commit();
			
		///
		} catch (Exception e) {
			System.out.println("please check the above steps" + e.getMessage());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
