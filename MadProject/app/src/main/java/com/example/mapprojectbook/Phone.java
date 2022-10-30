package com.example.mapprojectbook;

public class Phone {

    private int image;

    private String seller_email;

    private String phone_name;

    private String description;

    private Double price;

    private String condition;

    public Phone(String seller_email, String phone_name, String description, Double price, String condition, int image) {
        this.seller_email = seller_email;
        this.phone_name = phone_name;
        this.description = description;
        this.price = price;
        this.condition = condition;
        this.image = image;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public int getImage() {
        return image;
    }

    public String getPhone_name() {
        return phone_name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getCondition() {
        return condition;
    }
}
