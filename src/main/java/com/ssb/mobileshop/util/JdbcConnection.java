package com.ssb.mobileshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    public static Connection getConnection() {
        Connection con=null;
        try {
            // loading jdbc Driver

            Class.forName("com.mysql.cj.jdbc.Driver");
            // creating connection to mobileshop Dtabase

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileshop", "kishore",
                    "kishore19921");

        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Failed to create the database connection."); 
        }catch (ClassNotFoundException ex){
            System.out.println(ex);
        }
        return con;
    }
}
