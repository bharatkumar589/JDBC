package com.vd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionManagementWithBatchProcessing {

	public static final String driverClass = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sys";
	public static final String username = "root";
	public static final String password = "root";

	public static final String sql1 = "insert into Bank_Account_tbl values(11107,'xyz',10000)";
	public static final String sql2 = "insert into Bank_Account_tbl values(11108,'abc',20000)";
	public static final String sql3 = "update Bank_Account_tbl set accountHolderName='RajaRani' where accountNo=11101";

	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;
		boolean flag = false;

		try {

			// load and register driver
			Class.forName(driverClass);

			// create connefction
			con = DriverManager.getConnection(url, username, password);

			st = con.createStatement();

			// Disable auto commit mode on DB
			con.setAutoCommit(false);
			
			st.addBatch(sql1);
			st.addBatch(sql2);
			st.addBatch(sql3);

			int[] result = st.executeBatch();

			for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
				if (result[i] == 0) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				con.commit();
				System.out.println("Tx Successfull");
			} else {
				con.rollback();
				System.out.println("TX Failure");
			}
		} catch (Exception e) {
			System.out.println("please check the above steps" + e.getMessage());
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
