package mw.bbw.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public class ConnectionManagerSQL {

    private static ConnectionManagerSQL connManager = null;
    private static Connection conn = null;

    private ConnectionManagerSQL(){}

    public static Connection getConnection() {
        if (conn == null ) {
            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost/jokedb", "root", "1234");

                if (conn != null) {
                    System.out.println("Connection successful");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection failed! Message: " + e.getMessage());
            }
        }
        return conn;
    }

    public static ConnectionManagerSQL getInstance() {
        if(connManager == null) {
            connManager = new ConnectionManagerSQL();
        }
        return connManager;
    }
}