package board.model;

import java.io.Serializable;


public class Category implements Serializable {

    private int id;
    private String title;

    public Category() {
    }
    public Category(int id) {
        this.id = id;
    }
    public Category(String  title) {
        this.title = title;
    }
    public Category(int id, String  title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}