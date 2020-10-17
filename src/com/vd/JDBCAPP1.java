package com.vd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCAPP1 {

       // Added for Test
	public static final String driverClass="com.mysql.cj.jdbc.Driver";
	public static final String url="jdbc:mysql://localhost:3306/sys";
	public static final String username="root";
	public static final String password="root";
	
	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// Load and Register the driver
			Class.forName(driverClass);

			// Esstablishing the connection
			con = DriverManager.getConnection(url, username, password);

			// Create statement Object
			st = con.createStatement();

			// Send and execute the queries
			rs = st.executeQuery("select * from emp");

			System.out.println("Employee Details :");
			// iterate the resultset
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
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
