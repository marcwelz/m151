package mw.bbw;

import mw.bbw.dao.sql.DataBaseAccessSQL;
import mw.bbw.dao.access.DataBaseAcessAccess;
import mw.bbw.model.Joke;
import mw.bbw.model.Jokebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public class App
{
    public static void main( String[] args ) throws IOException {
        Jokebook jokebookSQL = new Jokebook(new DataBaseAccessSQL());
         BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        jokebookSQL.print();

        System.out.println("do you want to delete? y/n");
        String delete = reader.readLine();

        if(delete.equals("y")) {
            System.out.println("id?");
            String id = reader.readLine();
            System.out.println("are you sure to delete joke with id " + id + "? y/n");
            String answer = reader.readLine();
            if(answer.equals("y")) {
                jokebookSQL.delete(id);
                System.out.println("Joke deleted successfully");
            } else {
                System.out.println("Joke deleted failed");
            }
        }

        System.out.println("do you want to add a joke? y/n");
        String add = reader.readLine();

        if(add.equals("y")) {
            Joke joke = new Joke("", "", "", "");
            System.out.println("Set id");
            joke.setId(reader.readLine());
            System.out.println("set text");
            joke.setText(reader.readLine());
            System.out.println("set rating");
            joke.setRating(reader.readLine());

            jokebookSQL.addJoke(joke);
            System.out.println("Joke added successfully");
        }

        System.out.println("do you want to update a joke? y/n");
        String update = reader.readLine();

        if(update.equals("y")) {
            Joke joke = new Joke("", "", "", "");
            joke.setId(reader.readLine());
            joke.setText(reader.readLine());
            joke.setRating(reader.readLine());

            jokebookSQL.updateJoke(joke);
            System.out.println("Joke updated successfully");
        }

        if (false) {
            Jokebook jokebookAccess = new Jokebook(new DataBaseAcessAccess());

        }
    }
}
