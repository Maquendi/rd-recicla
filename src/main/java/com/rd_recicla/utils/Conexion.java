package com.rd_recicla.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private Connection conn = null;

	public Conexion(){
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public Connection connect(){

	try {
			String url = "jdbc:mysql://localhost:3306/reciclajedb";
	
			if(conn == null || conn.isClosed()){
			   conn = DriverManager.getConnection(url,"root","");
			}
			
		   } catch (SQLException e) {
			  e.printStackTrace();
		}
	
		return conn;
	}
	
	
	public void disconect()
	{
		try {
			if(!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
