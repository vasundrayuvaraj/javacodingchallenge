package com.hexaware.insurancemanagementsystem.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String fileName) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String db = props.getProperty("database");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            return "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + password;
        } catch (IOException e) {
            System.err.println("Error reading database property file: " + e.getMessage());
            return null;
        }
    }
}
