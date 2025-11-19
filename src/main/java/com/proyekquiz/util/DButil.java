package com.proyekquiz.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil{

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/webproquiz2";
	private static final String JDBC_USER = "root"; 
	private static final String JDBC_PASSWORD = ""; 


    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            // fatal error jika driver tidak ditemukan
            throw new IllegalStateException("Gagal memuat MySQL JDBC Driver!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}