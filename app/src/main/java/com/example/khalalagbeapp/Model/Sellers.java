package com.example.khalalagbeapp.Model;

public class Sellers {
    private String name, category, date,description,image,time,sid, type1_price,type2_price,
            type3_price,presentAddress ,phone,identity,workingAreas,password;
    public Sellers()
    {

    }

    public Sellers(String name, String category, String date, String description, String image, String time, String sid, String type1_price, String type2_price, String type3_price, String presentAddress, String phone, String identity, String workingAreas, String password) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.description = description;
        this.image = image;
        this.time = time;
        this.sid = sid;
        this.type1_price = type1_price;
        this.type2_price = type2_price;
        this.type3_price = type3_price;
        this.presentAddress = presentAddress;
        this.phone = phone;
        this.identity = identity;
        this.workingAreas = workingAreas;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getType1_price() {
        return type1_price;
    }

    public void setType1_price(String type1_price) {
        this.type1_price = type1_price;
    }

    public String getType2_price() {
        return type2_price;
    }

    public void setType2_price(String type2_price) {
        this.type2_price = type2_price;
    }

    public String getType3_price() {
        return type3_price;
    }

    public void setType3_price(String type3_price) {
        this.type3_price = type3_price;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getWorkingAreas() {
        return workingAreas;
    }

    public void setWorkingAreas(String workingAreas) {
        this.workingAreas = workingAreas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
