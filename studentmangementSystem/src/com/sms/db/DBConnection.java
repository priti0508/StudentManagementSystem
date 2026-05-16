package com.sms.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sms_db",
                    "root",
                    ""
            );

            System.out.println("Database Connected");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}