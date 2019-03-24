package ca.ulaval.ima.tp3;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentOffer {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("model")
    @Expose
    private ContentModel model;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("from_owner")
    @Expose
    private Boolean fromOwner;
    @SerializedName("kilometers")
    @Expose
    private Integer kilometers;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("created")
    @Expose
    private String created;

    /**
     * No args constructor for use in serialization
     *
     */
    public ContentOffer() {
    }

    /**
     *
     * @param id
     * @param model
     * @param price
     * @param created
     * @param kilometers
     * @param image
     * @param year
     * @param fromOwner
     */
    public ContentOffer(Integer id, ContentModel model, String image, Integer year, Boolean fromOwner, Integer kilometers, Integer price, String created) {
        super();
        this.id = id;
        this.model = model;
        this.image = image;
        this.year = year;
        this.fromOwner = fromOwner;
        this.kilometers = kilometers;
        this.price = price;
        this.created = created;
    }

    public ContentOffer(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContentModel getModel() {
        return model;
    }

    public void setModel(ContentModel model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getFromOwner() {
        return fromOwner;
    }

    public void setFromOwner(Boolean fromOwner) {
        this.fromOwner = fromOwner;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
