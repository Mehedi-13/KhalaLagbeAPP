package com.example.khalalagbeapp.Model;

public class AdminOrders
{
    private String address,city,date,mname,phone,startingDayForWork,state,time,totalSalary;

    public AdminOrders() {
    }

    public AdminOrders(String address, String city, String date, String mname, String phone, String startingDayForWork, String state, String time, String totalSalary) {
        this.address = address;
        this.city = city;
        this.date = date;
        this.mname = mname;
        this.phone = phone;
        this.startingDayForWork = startingDayForWork;
        this.state = state;
        this.time = time;
        this.totalSalary = totalSalary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartingDayForWork() {
        return startingDayForWork;
    }

    public void setStartingDayForWork(String startingDayForWork) {
        this.startingDayForWork = startingDayForWork;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }
}
