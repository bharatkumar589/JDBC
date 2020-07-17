package com.vd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableStatementApp {

	public static final String driverClass = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sys";
	public static final String username = "root";
	public static final String password = "root";

	public static void main(String[] args) {

		Connection con = null;
		CallableStatement stmt = null;
		Scanner sc = null;
		try {
			// load and register driver
			Class.forName(driverClass);

			// create connection
			con = DriverManager.getConnection(url, username, password);

			// reading input from keyboard
			sc = new Scanner(System.in);
			System.out.println("Please Enter Account Number::");
			int accountNo = sc.nextInt();

			// Execute a query
			String sql = "{call getAccountBalance (?, ?)}";
			stmt = con.prepareCall(sql);
			stmt.setInt(1, accountNo);
			stmt.registerOutParameter(2, java.sql.Types.INTEGER);

			// Use execute method to run stored procedure.
			System.out.println("Executing stored procedure...");
			stmt.execute();
			
			int accountBalance=stmt.getInt(2);
			System.out.println("Available account balance ::"+accountBalance);

		} catch (Exception e) {

		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
