package com.wipro.billing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getDBConnection() {

        Connection con = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "hr";
            String password = "hr";

            con = DriverManager.getConnection(url, username, password);
            System.out.println("Oracle DB Connected Successfully");

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }

        return con;
    }
}

