/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author antho
 */
public class SQLDataBaseConnection {
    private static final String connectionString =
                "jdbc:sqlserver://LAPTOG-AGG\\SQLEXPRESS:50413;"
                + "database=CORPORACION;"
                + "user=corporacion;"
                + "password=corporacion;"
                + "encrypt=false;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

    private SQLDataBaseConnection () {
    }
    
    public static Connection getConnection(){
        try {
            Connection connection;
            connection = DriverManager.getConnection(connectionString);
            return connection;
        } catch (SQLException ex) { 
            ex.printStackTrace();
        }
        return null;
    }
        
        
        
}

