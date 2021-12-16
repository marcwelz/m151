package mw.bbw.model;

/**
 * @author marcwelz
 * @version 31.08.2021
 */

public class Joke {

    private String id;
    private String text;
    private String rating;
    private String date;

    public Joke(String id, String text, String rating, String date) {
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.date = date;
    }

    public void print() {
        System.out.println("id: " + this.id  + ", joke: " + this.text + ", rating: " + this.rating  + ", date: " + this.date);
        System.out.println();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
