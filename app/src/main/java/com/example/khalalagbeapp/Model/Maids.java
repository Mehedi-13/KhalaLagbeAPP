package com.example.khalalagbeapp.Model;

public class Maids
{
   private String mname, category, date,description,image,time,mid, type1_price,type2_price,
            type3_price,present_address ,maid_PhoneNumber,productState;

    public Maids()
    {

    }

    public Maids(String mname, String category, String date, String description, String image, String time,
                 String mid, String type1_price, String type2_price,
                 String type3_price, String present_address, String maid_PhoneNumber, String productState) {
        this.mname = mname;
        this.category = category;
        this.date = date;
        this.description = description;
        this.image = image;
        this.time = time;
        this.mid = mid;
        this.type1_price = type1_price;
        this.type2_price = type2_price;
        this.type3_price = type3_price;
        this.present_address = present_address;
        this.maid_PhoneNumber = maid_PhoneNumber;
        this.productState = productState;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public String getMaid_PhoneNumber() {
        return maid_PhoneNumber;
    }

    public void setMaid_PhoneNumber(String maid_PhoneNumber) {
        this.maid_PhoneNumber = maid_PhoneNumber;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }
}
