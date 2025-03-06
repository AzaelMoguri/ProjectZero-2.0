package Revature.com.Model;

public class Address {
    private int idAddress;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    public Address(int idAddress, String street, String city, String state, String zip, String country) {
        this.idAddress = idAddress;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public Address() {}

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {this.idAddress = idAddress;}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) { this.street = street;}

    public String getCity() {
        return city;
    }

    public void setCity(String city) { this.city = city;}

    public String getState() {
        return state;
    }

    public void setState(String state) { this.state = state;}

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) { this.zip = zip;}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) { this.country = country;}
}


