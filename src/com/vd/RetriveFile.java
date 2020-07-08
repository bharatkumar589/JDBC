package com.vd;

import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetriveFile {

	public static final String driverClass = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sys";
	public static final String username = "root";
	public static final String password = "root";

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Clob clob = null;
		Reader reader = null;
		FileWriter fw = null;

		try {
			// Load and Register the driver
			Class.forName(driverClass);
			// Esstablishing the connection
			con = DriverManager.getConnection(url, username, password);

			// create PreparedStatement
			ps = con.prepareStatement("select * from file_tbl");

			// Execute query
			rs = ps.executeQuery();

			while (rs.next()) {
				
				// writing to file
				clob = rs.getClob("file");
				reader = clob.getCharacterStream();
				String filePath = "D:\\output.txt";
				fw = new FileWriter(filePath);
				int i;
				while ((i = reader.read()) != -1) {
					fw.write(i);
				}
				fw.close();
				System.out.println("File Retrived successfully");
			}

			

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
