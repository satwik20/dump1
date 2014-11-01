package com.cts.bms_DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	
		//creating connection to a database
		public static Connection getConnection() {
			Connection con = null;
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				String uname = "bms";  //username for the database
				String pass = "bms";   //password for the database
				String url = "jdbc:oracle:thin:@//10.229.53.38:1521/XE";			
				con = DriverManager.getConnection( url, uname,pass);
				System.out.println("connection Successfull");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				System.exit(0);

			}
			return con;

		}
		
}
