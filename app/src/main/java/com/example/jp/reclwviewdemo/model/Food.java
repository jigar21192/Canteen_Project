package com.example.jp.reclwviewdemo.model;

public class Food {

    private String name;
    private String imurl;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public Food(String name, String imurl,String price) {
        this.name = name;
        this.imurl = imurl;
        this.price = price;
    }

    public Food(){

}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImurl() {
        return imurl;
    }

    public void setImurl(String imurl) {
        this.imurl = imurl;
    }

  /*  public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }*/

}
