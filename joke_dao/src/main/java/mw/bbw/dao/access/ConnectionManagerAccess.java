package mw.bbw.dao.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public class ConnectionManagerAccess {
    private static ConnectionManagerAccess connManager = null;
    private static Connection conn = null;

    private ConnectionManagerAccess(){}

    public static Connection getConnection() {
        if (conn == null ) {
            try{
                //todo access links
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

    public static ConnectionManagerAccess getInstance() {
        if(connManager == null) {
            connManager = new ConnectionManagerAccess();
        }
        return connManager;
    }
}
