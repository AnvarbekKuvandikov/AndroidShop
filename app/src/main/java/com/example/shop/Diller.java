package com.example.shop;

import java.io.Serializable;

public class Diller  implements Serializable {
    private Integer id;
    private String name;
    private String phone;
    private Integer clientId;

    public Diller() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Diller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
