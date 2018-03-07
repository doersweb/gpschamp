package track.gpschamp.com.gpschamp.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 04/02/18.
 */

public class UserInfo {


    @SerializedName("name")
    private String name;

    @SerializedName("company")
    private String company;

    @SerializedName("address")
    private String address;

    @SerializedName("post_code")
    private String postCode;

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("phone1")
    private String primaryPhone;

    @SerializedName("phone2")
    private String secondayPhone;

    @SerializedName("email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondayPhone() {
        return secondayPhone;
    }

    public void setSecondayPhone(String secondayPhone) {
        this.secondayPhone = secondayPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
