package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/mydb";
            connection = DriverManager.getConnection(url, "root", "sapassword");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
