package com.vd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatemntApp {

	public static final String driverClass = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sys";
	public static final String username = "root";
	public static final String password = "root";

	public static void main(String[] args) {

		//Tetsting1
		Connection con = null;
		PreparedStatement ps = null;
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Please eneter your details");
		System.out.println("Enter id::");
		int id=sc.nextInt();
		
		System.out.println("Enter name:");
		String name=sc.next();
		
		System.out.println("Emter Address::");
		String addrs=sc.next();
		

		try {
			// Load and Register the driver
			Class.forName(driverClass);
			// Esstablishing the connection
			con = DriverManager.getConnection(url, username, password);

			// create prepared statement object
			ps = con.prepareStatement("insert into emp values(?,?,?)");

			// setting param to query
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, addrs);

			int result = ps.executeUpdate();

			if (result == 0) {
				System.out.println("please check the query");
			} else {
				System.out.println("Successfully Inserted");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
