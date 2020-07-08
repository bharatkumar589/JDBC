package com.vd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlInjection {

	public static final String driverClass="com.mysql.cj.jdbc.Driver";
	public static final String url="jdbc:mysql://localhost:3306/sys";
	public static final String username="root";
	public static final String password="root";
	public static final String sql="";
	
	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		// Reading inputs from 
		System.out.println("Please Enter Your Credentials");
		Scanner sc=new Scanner(System.in);
		System.out.println("Please Enter Username::");
		String uname=sc.next();
		System.out.println("Please Enter Password::");
		String pswd=sc.next();
		

		try {
			// Load and Register the driver
			Class.forName(driverClass);

			// Establishing the connection
			con = DriverManager.getConnection(url, username, password);

			// Create statement Object
			st = con.createStatement();

			// Send and execute the queries
			rs= st.executeQuery("select * from Login_tbl where uname="+uname+" and pswd ="+pswd);
			
			// iterate the resultset
			System.out.println("Employee Details :");
			if(rs.next()) {
				System.out.println("User Logged in successfully");
			}else {
				System.out.println("Please check user details");
			}
		} catch (Exception e) {
			System.out.println("Check the standard steps" + e.getMessage());
		}

		finally {
			// CLose the connnections
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

}
