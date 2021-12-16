package mw.bbw.dao;

import mw.bbw.model.Joke;
import java.util.ArrayList;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public interface DataBaseAccess {
    public abstract ArrayList<Joke> getAllJokes();
    public abstract void deleteJoke(String id);
    public abstract void addJoke(Joke joke);
    public abstract void updateJoke(Joke joke);
    public abstract Joke getJokeById(String id);
}
