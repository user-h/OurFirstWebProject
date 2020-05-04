package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection conn = null;
	private static String driver = "com.mysql.cj.jdbc.Driver";//com.mysql.cj.jdbc.Driver
	private static String url = "jdbc:mysql://localhost:3306/studentManager6?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";
	//jdbc:mysql://localhost:3306/studentManager6?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC"
	private static String user = "root";
	private static String pass = "123456789";

	public static Connection getConnection() {

		if (conn == null) {
			System.out.println("new connection");
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,user,pass);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} else {
			//System.out.println("not new connection");
		}

		return conn;

	}
	public static void main(String[] args) {
		System.out.println(ConnectionUtil.getConnection());
	}
}

