package ca.ulaval.ima.tp3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailContent {

    @SerializedName("_contentoffer")
    @Expose
    private ContentOffer _contentoffer;
    @SerializedName("_description")
    @Expose
    private String _description;
    @SerializedName("_seller")
    @Expose
    private Seller _seller;

    /**
     * No args constructor for use in serialization
     *
     */
    public DetailContent(){

    }

    public DetailContent(ContentOffer contentOffer, String _description, Seller seller){
        _contentoffer = contentOffer;
        _description = _description;
        _seller = seller;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public ContentOffer getContentoffer() {
        return _contentoffer;
    }

    public void setContentoffer(ContentOffer contentoffer) {
        this._contentoffer = contentoffer;
    }

    public Seller getSeller() {
        return _seller;
    }

    public void setSeller(Seller seller) {
        this._seller = seller;
    }

}
