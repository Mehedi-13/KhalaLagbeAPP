package com.example.khalalagbeapp.Model;

public class Cart {
    private String mid, mname,quantity,type, type_price,maid_PhoneNumber;

    public Cart() {
    }

    public Cart(String mid, String mname, String quantity, String type, String type_price, String maid_PhoneNumber) {
        this.mid = mid;
        this.mname = mname;
        this.quantity = quantity;
        this.type = type;
        this.type_price = type_price;
        this.maid_PhoneNumber = maid_PhoneNumber;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_price() {
        return type_price;
    }

    public void setType_price(String type_price) {
        this.type_price = type_price;
    }

    public String getMaid_PhoneNumber() {
        return maid_PhoneNumber;
    }

    public void setMaid_PhoneNumber(String maid_PhoneNumber) {
        this.maid_PhoneNumber = maid_PhoneNumber;
    }
}
