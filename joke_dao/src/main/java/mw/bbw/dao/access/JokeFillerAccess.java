package mw.bbw.dao.access;

import mw.bbw.model.Joke;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public class JokeFillerAccess {

    private static void fillJokeObject(Joke joke, ResultSet entry) throws SQLException {

        joke.setId(entry.getString("id"));
        joke.setText(entry.getString("text"));
        joke.setRating(entry.getString("rating"));
        joke.setDate(entry.getString("date"));
    }

    public static Joke createJokeObject(ResultSet entry)throws SQLException{
        Joke joke = new Joke("", "", "", "");

        fillJokeObject(joke,entry);

        return joke;

    }
}
