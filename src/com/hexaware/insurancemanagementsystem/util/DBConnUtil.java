package com.hexaware.insurancemanagementsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnUtil {
    private static final String PROP_FILE = "db.properties";

    public static Connection getConnection() {
        String connStr = DBPropertyUtil.getConnectionString(PROP_FILE);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
        }
        return conn;
    }

    public static void closePreparedStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
