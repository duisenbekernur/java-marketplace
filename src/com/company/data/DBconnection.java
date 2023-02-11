package com.company.data;

import java.sql.*;

public class DBconnection {

    public static Connection connection() throws SQLException{
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgres", "Ernur2005");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
