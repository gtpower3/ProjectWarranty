package com.example.projectwarranty;

public class Product {
    private String name;
    private int type;

    public Product(){
        name = "";
        type = -1;
    }

    public Product(String n, int t){
        name = n;
        type = t;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
