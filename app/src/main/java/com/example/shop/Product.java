package com.example.shop;

public class Product {
    private Integer putId;
    private Integer id;
    private String name;
    private Double price;
    private Double inprice;
    private Integer count;
    private Integer incount;
    private  String shtrix;
    private Integer incnt;

    public Product() {
    }

    public Product(Integer putId, Integer id, String name, Double price, Double inprice, Integer count, Integer incount) {
        this.putId = putId;
        this.id = id;
        this.name = name;
        this.price = price;
        this.inprice = inprice;
        this.count = count;
        this.incount = incount;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(Integer id, String name, Double price, Double inprice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inprice = inprice;
    }


    public Product(String name, Double price, Double inprice) {
        this.name = name;
        this.price = price;
        this.inprice = inprice;
    }

    public Integer getIncnt() {
        return incnt;
    }

    public void setIncnt(Integer incnt) {
        this.incnt = incnt;
    }

    public String getShtrix() {
        return shtrix;
    }

    public void setShtrix(String shtrix) {
        this.shtrix = shtrix;
    }

    public Integer getPutId() {
        return putId;
    }

    public void setPutId(Integer putId) {
        this.putId = putId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIncount() {
        return incount;
    }

    public void setIncount(Integer incount) {
        this.incount = incount;
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


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInprice() {
        return inprice;
    }

    public void setInprice(Double inprice) {
        this.inprice = inprice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "putId=" + putId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inprice=" + inprice +
                ", count=" + count +
                ", incount=" + incount +
                ", shtrix='" + shtrix + '\'' +
                '}';
    }
}
