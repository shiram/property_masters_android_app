package blacksantatech.com.propertymasters.other;

public class Estate {

    private int estate_id;
    private int id;
    private  String estate_name;
    private String estate_price;
    private String estate_description;
    private String estate_category;
    private String estate_image;
    private String estate_country;
    private String estate_city;
    private String estate_address_onr;
    private String estate_address_two;

    public Estate(int estate_id, int id, String estate_name, String estate_price, String estate_description, String estate_category, String estate_image, String estate_country, String estate_city, String estate_address_onr, String estate_address_two) {
        this.estate_id = estate_id;
        this.id = id;
        this.estate_name = estate_name;
        this.estate_price = estate_price;
        this.estate_description = estate_description;
        this.estate_category = estate_category;
        this.estate_image = estate_image;
        this.estate_country = estate_country;
        this.estate_city = estate_city;
        this.estate_address_onr = estate_address_onr;
        this.estate_address_two = estate_address_two;
    }

    public int getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(int estate_id) {
        this.estate_id = estate_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstate_name() {
        return estate_name;
    }

    public void setEstate_name(String estate_name) {
        this.estate_name = estate_name;
    }

    public String getEstate_price() {
        return estate_price;
    }

    public void setEstate_price(String estate_price) {
        this.estate_price = estate_price;
    }

    public String getEstate_description() {
        return estate_description;
    }

    public void setEstate_description(String estate_description) {
        this.estate_description = estate_description;
    }

    public String getEstate_category() {
        return estate_category;
    }

    public void setEstate_category(String estate_category) {
        this.estate_category = estate_category;
    }

    public String getEstate_image() {
        return estate_image;
    }

    public void setEstate_image(String estate_image) {
        this.estate_image = estate_image;
    }

    public String getEstate_country() {
        return estate_country;
    }

    public void setEstate_country(String estate_country) {
        this.estate_country = estate_country;
    }

    public String getEstate_city() {
        return estate_city;
    }

    public void setEstate_city(String estate_city) {
        this.estate_city = estate_city;
    }

    public String getEstate_address_onr() {
        return estate_address_onr;
    }

    public void setEstate_address_onr(String estate_address_onr) {
        this.estate_address_onr = estate_address_onr;
    }

    public String getEstate_address_two() {
        return estate_address_two;
    }

    public void setEstate_address_two(String estate_address_two) {
        this.estate_address_two = estate_address_two;
    }
}
