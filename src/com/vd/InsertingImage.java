package com.vd;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertingImage {

	//Variables
	
	public static final String driverClass = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sys";
	public static final String username = "root";
	public static final String password = "root";
	
	
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			//Added
			
			// Load and Register the driver
			Class.forName(driverClass);
			// Establishing the connection
			con = DriverManager.getConnection(url, username, password);
			
			// getting image form below path
			//File file=new File("C:\\Users\\kbharat\\Desktop\\vid\\jdbc brief introduction_img.png");
			File file=new File("C:\\Users\\kbharat\\Desktop\\vid\\vd.txt");
			
			FileInputStream fis=new FileInputStream(file);
			
			ps=con.prepareStatement("insert into image_tbl (name,image) values(?,?)"); 
			ps.setString(1,"image-1");
			ps.setBinaryStream(2,fis,(int)file.length());
			int result=ps.executeUpdate();
			
			if(result ==0) {
				System.out.println("Image not inserted ...please chec ");
			}else {
				System.out.println("Image inderted secessfully");
			}
			
		}catch(Exception e) {
			System.out.println("please check the above steps"+e.getMessage());
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//end

		}

	}
}
