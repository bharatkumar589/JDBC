package com.vd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SolutionForSqlInjection {

	public static final String driverClass="com.mysql.cj.jdbc.Driver";
	public static final String url="jdbc:mysql://localhost:3306/sys";
	public static final String username="root";
	public static final String password="root";
	public static final String sql="select * from Login_tbl where uname=? and pswd=?";
	
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
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

			// Esstablishing the connection
			con = DriverManager.getConnection(url, username, password);

			// Create statement Object
			ps = con.prepareStatement(sql);

			//setting parameters
			ps.setString(1,uname);
			ps.setString(2,pswd);
			// Send and execute the queries
			rs=ps.executeQuery();
			
			System.out.println("Employee Details :");
			// iterate the resultset
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
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

}
