package com.company.data;

import java.sql.*;

public class DBconnection {

    public static Connection connection(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgres", "1079");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
