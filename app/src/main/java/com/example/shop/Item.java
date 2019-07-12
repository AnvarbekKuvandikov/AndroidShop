package com.example.shop;

public class Item {
     private Integer id;
     private String Name;
     private Integer count;
     private Integer price;
     private Integer sum;
    public Item(Integer id, String name, Integer count, Integer price, Integer sum) {
        this.id = id;
        Name = name;
        this.count = count;
        this.price = price;
        this.sum = sum;
    }

    public Item(String name, Integer count, Integer price, Integer sum) {
        Name = name;
        this.count = count;
        this.price = price;
        this.sum = sum;
    }

    public Item() {
    }

    public String getName() {
        return Name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
