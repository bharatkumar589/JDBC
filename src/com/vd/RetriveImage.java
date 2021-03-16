package com.vd;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetriveImage {
	
	public static final String driverClass = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sys";
	public static final String username = "root";
	public static final String password = "root";
	// Testing
	
	public static void main(String[] args) {
		
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			// Load and Register the driver
			Class.forName(driverClass);
			// Esstablishing the connection
			con = DriverManager.getConnection(url, username, password);
			
			// file path for retrived image
			File file=new File("D:\\image1.png");
			FileOutputStream fos=new FileOutputStream(file);
			byte b[];
			Blob blob;
			
		    ps=con.prepareStatement("select * from image_tbl"); 
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				blob=rs.getBlob("image");
				b=blob.getBytes(1,(int)blob.length());
				fos.write(b);
			}
			System.out.println("Imgae Rerived successfully to "+file.getPath()+"  path");
			
		}catch(Exception e) {
			System.out.println("please check the above steps"+e.getMessage());
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
