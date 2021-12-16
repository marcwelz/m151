package mw.bbw.model;

import mw.bbw.dao.DataBaseAccess;
import java.util.ArrayList;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public class Jokebook {

    private ArrayList<Joke> jokes = new ArrayList<>();
    private DataBaseAccess dao;

    public Jokebook(DataBaseAccess dao){
        this.dao = dao;
    }

    public void print() {
        jokes = dao.getAllJokes();
        for(Joke i : jokes) {
            i.print();
        }
    }

    public void delete(String id) {
        dao.deleteJoke(id);
    }

    public void addJoke(Joke joke) {
        dao.addJoke(joke);
    }

    public void updateJoke(Joke joke) {
        dao.updateJoke(joke);
    }

    public Joke printJokeById(String id) {
        return dao.getJokeById(id);
    }
}
