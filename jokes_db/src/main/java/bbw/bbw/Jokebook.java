package bbw.bbw;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public class Jokebook {

    public ArrayList<Joke> jokes = new ArrayList<>();

    public Jokebook(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost/jokedb";

            Connection connection = DriverManager.getConnection(connectionUrl, "root", "1234");
            Statement stmt = connection.createStatement();
            ResultSet entries = stmt.executeQuery("SELECT * FROM joke");

            while (entries.next()) {
                jokes.add(JokeFiller.createJokeObject(entries));
                print();
            }

            entries.close();
            stmt.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for(Joke i : jokes) {
            i.print();
        }
    }
}
