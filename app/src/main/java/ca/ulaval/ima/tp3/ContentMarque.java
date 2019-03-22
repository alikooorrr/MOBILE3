package ca.ulaval.ima.tp3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentMarque {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ContentMarque(int id, String name){
        this.id = id;
        this.name = name;
    }
}
