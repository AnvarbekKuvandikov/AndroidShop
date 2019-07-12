package com.example.shop;

public class Product {
    Integer id;
    String name;
    Integer price;
    Integer inprice;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(Integer id, String name, Integer price, Integer inprice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inprice = inprice;
    }

    public Product(String name, Integer price, Integer inprice) {
        this.name = name;
        this.price = price;
        this.inprice = inprice;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getInprice() {
        return inprice;
    }

    public void setInprice(Integer inprice) {
        this.inprice = inprice;
    }
}
