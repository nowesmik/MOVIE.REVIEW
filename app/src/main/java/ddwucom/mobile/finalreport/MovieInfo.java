package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MovieInfo implements Serializable {
    private int imageId;
    private int id;
    private String name;
    private String actor;
    private String director;
    private String rate;
    private String comment;

    public MovieInfo(){
        this.imageId = 0;
        this.id = 0;
        this.name = null;
        this.actor = null;
        this.director = null;
    }

    public MovieInfo(int imageId, int id, String name, String actor, String director, String rate, String plot) {
        this.imageId = imageId;
        this.id = id;
        this.name = name;
        this.actor = actor;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NonNull
    @Override
    public String toString() {
        return name + ", " + actor + ", " + director;
    }
}
