package com.example.shop;

public class Item {
     private Integer id;
     private String name;
     private Integer count;
     private Integer incount;
     private Integer price;
     private Integer inprice;

    public Item() {
    }

    public Item(Integer id, String name, Integer count, Integer incount, Integer price, Integer inprice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.incount = incount;
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", incount=" + incount +
                ", price=" + price +
                ", inprice=" + inprice +
                '}';
    }
}
