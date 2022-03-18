package com.example.homework8;


public class Bottle {
    private String name;
    private double size;
    private double price;

    public Bottle(){
        name = "Default";
        size = 0.0;
        price = 0.0;

    }
    public Bottle(String name, double size, double price){
        this.name = name;
        this.size = size;
        this.price = price;

    }
    public void setName(String NAME){
        name = NAME;
    }
    public String getName(){
        return name;
    }
    public void setSize(double Siz){
        this.size = Siz;
    }
    public double getSize(){
        return this.size;
    }
    public void setPrice(double size){
        if(size == 1.0){
            this.price = 3;
        }
        else if (size == 0.75){
            this.price = 2;
        }
        else if(size == 0.5){
            this.price = 1;
        }
        else{
            this.price = 0;
        }
    }
    public double getPrice(){
        return price;

    }
}