package board.model;

import java.io.Serializable;
public class Favorit implements Serializable {
    private int id;
    private long advertisement_id;
    private long user_id;

    Favorit(){

    }
    public Favorit(long advertisement_id, long user_id){
        this.advertisement_id = advertisement_id;
        this.user_id = user_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAdvertisement_id() {
        return advertisement_id;
    }

    public void setAdvertisement_id(long advertisement_id) {
        this.advertisement_id = advertisement_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}