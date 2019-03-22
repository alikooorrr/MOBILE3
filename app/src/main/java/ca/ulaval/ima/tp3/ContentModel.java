package ca.ulaval.ima.tp3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentModel {


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("brand")
    @Expose
    private ContentMarque brand;
    @SerializedName("name")
    @Expose
    private String name;


    /**
     *
     * @param id
     * @param name
     */
    public ContentModel(int id,String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContentMarque getBrand() {
        return brand;
    }

    public void setContentMarque(ContentMarque contentMarque) {
        this.brand = contentMarque;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

