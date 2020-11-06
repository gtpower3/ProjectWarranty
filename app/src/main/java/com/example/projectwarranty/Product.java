package com.example.projectwarranty;

public class Product {
    String name;
    int type;

    public Product(String n, int t){
        name = n;
        type = t;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
