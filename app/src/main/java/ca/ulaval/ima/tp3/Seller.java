package ca.ulaval.ima.tp3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seller {

    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * No args constructor for use in serialization
     *
     */
    public Seller() {
    }

    /**
     *
     * @param lastName
     * @param email
     * @param firstName
     */
    public Seller(String lastName, String firstName, String email) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
