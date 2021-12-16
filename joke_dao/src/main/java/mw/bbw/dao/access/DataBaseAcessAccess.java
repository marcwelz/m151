package mw.bbw.dao.access;

import mw.bbw.dao.sql.ConnectionManagerSQL;
import mw.bbw.dao.DataBaseAccess;
import mw.bbw.dao.sql.JokeFillerSQL;
import mw.bbw.model.Joke;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public class DataBaseAcessAccess implements DataBaseAccess {

    public ArrayList<Joke> jokes = new ArrayList<>();
    private Connection con;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");

    public DataBaseAcessAccess() {
        con = ConnectionManagerSQL.getInstance().getConnection();
    }

    @Override
    public ArrayList<Joke> getAllJokes() {
        try {
            Statement stmt = con.createStatement();
            ResultSet entries = stmt.executeQuery("SELECT * FROM joke");

            while (entries.next()) {
                jokes.add(JokeFillerAccess.createJokeObject(entries));
            }

            entries.close();
            stmt.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return jokes;
    }

    @Override
    public void deleteJoke(String id) {
        try {
            String deleteSQL = "DELETE FROM joke WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addJoke(Joke joke) {
        try {
            String query = " insert into joke (id, text, rating, date)"
                    + " values (?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, joke.getId());
            preparedStmt.setString(2, joke.getText());
            preparedStmt.setString(3, joke.getRating());
            preparedStmt.setString(4, LocalDateTime.now().format(formatter));

            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateJoke(Joke joke) {
        try {
            System.out.println("your current joke:");
            System.out.println(jokes.get(Integer.parseInt(joke.getId())));

            PreparedStatement prepStmt = con
                    .prepareStatement("UPDATE joke SET text = ?, rating = ?, date = ? WHERE id = ?");

            prepStmt.setString(1, joke.getText());
            prepStmt.setString(2, joke.getRating());
            prepStmt.setString(3, joke.getDate());
            prepStmt.setString(4, LocalDateTime.now().format(formatter));

            if (prepStmt.executeUpdate() <= 0) {
                System.out.println("Update Joke was not possible");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Joke getJokeById(String id) {
        ArrayList<Joke> jokes2 = new ArrayList<>();
        try {
            PreparedStatement prepStmtOldPassword = con
                    .prepareStatement("SELECT * FROM joke WHERE id=?");

            ResultSet resultPassword = prepStmtOldPassword.executeQuery();
            if(resultPassword.next()) {
                jokes2.add(JokeFillerSQL.createJokeObject(resultPassword));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Joke(jokes2.get(0).getId(), jokes2.get(0).getText(), jokes2.get(0).getRating(), jokes2.get(0).getDate());
    }
}
