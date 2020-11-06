package com.example.projectwarranty;

import java.util.Date;

public class Warranty {
    private Product product;
    private int startDay;
    private int startMonth;
    private int startYear;
    private int warrantyLength;
    private Date epochDate;

    public Warranty(){
        product = new Product();
        startDay = -1;
        startMonth = -1;
        startYear = -1;
        warrantyLength = -1;
    }

    public Warranty(Product p, int d, int m, int y, int w){
        product = p;
        startDay = d;
        startMonth = m;
        startYear = y;
        warrantyLength = w;
        epochDate = toDate();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setWarrantyLength(int warrantyLength) {
        this.warrantyLength = warrantyLength;
    }

    public void setEpochDate(Date epochDate){
        this.epochDate = epochDate;
    }

    public Product getProduct() {
        return product;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getWarrantyLength() {
        return warrantyLength;
    }

    public String getDate(){
        return (startYear + "-" + (startMonth + 1) + "-" + startDay);
    }

    public Date toDate(){
        return new Date(startYear, startMonth, startDay);
    }

    @Override
    public String toString() {
        return "Warranty{" +
                "product=" + product.toString() +
                ", startDay=" + startDay +
                ", startMonth=" + startMonth +
                ", startYear=" + startYear +
                ", warrantyLength=" + warrantyLength +
                '}';
    }
}
