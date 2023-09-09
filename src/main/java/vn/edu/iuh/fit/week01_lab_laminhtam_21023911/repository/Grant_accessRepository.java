package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class Grant_accessRepository {
    Connection connection;

    public Grant_accessRepository() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3306/mydb";
        connection = DriverManager.getConnection(url, "root", "sapassword");
    }
}
