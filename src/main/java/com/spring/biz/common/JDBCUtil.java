package com.spring.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCUtil {
	static final String driverName="com.mysql.cj.jdbc.Driver";
	static final String url="jdbc:mysql://localhost/namdb";
	static final String user="root";
	static final String passwd="1234";
	public static Connection connect() {
		Connection conn=null;
		try {
			Class.forName(driverName);
			conn=DriverManager.getConnection(url, user, passwd);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("   로그: connect() 수행완료");
		}
		return conn;
	}
	public static void disconnect(Connection conn,PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("   로그: disconnect() 수행완료");
		}		
	}
}
