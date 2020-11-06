package com.example.projectwarranty;

public class Warranty {
    Product product;
    int startDay;
    int startMonth;
    int startYear;
    int warrantyLength;

    public Warranty(Product p, int d, int m, int y, int w){
        product = p;
        startDay = d;
        startMonth = m;
        startYear = y;
        warrantyLength = w;
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
