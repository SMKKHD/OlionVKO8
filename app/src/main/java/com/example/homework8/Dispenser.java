package com.example.homework8;

import java.util.ArrayList;

public class Dispenser {
    private static Dispenser disp = new Dispenser();
    private int bottles;
    // The array for the com.example.homework8.Bottle-objects
    private ArrayList <Bottle> bottle_list;
    private double money;

    public Dispenser() {
        bottles = 5;
        money = 0.0;
        bottle_list = new ArrayList <Bottle>();
        for(int i = 0;i<bottles;i++) {
            Bottle bottle = new Bottle();
            if(i == 0){
                bottle.setName("Coke");
                bottle.setSize(0.5);
                bottle.setPrice(bottle.getSize());
                bottle_list.add(bottle);
            }
            else if(i == 1){
                bottle.setName("Pepsi");
                bottle.setSize(0.5);
                bottle.setPrice(bottle.getSize());
                bottle_list.add(bottle);

            }
            else if(i == 2){
                bottle.setName("Fanta");
                bottle.setSize(0.75);
                bottle.setPrice(bottle.getSize());
                bottle_list.add(bottle);

            }
            else if(i == 3){
                bottle.setName("Jaffa");
                bottle.setSize(1.0);
                bottle.setPrice(bottle.getSize());
                bottle_list.add(bottle);

            }
            else if(i == 4){
                bottle.setName("Muumi");
                bottle.setSize(1.0);
                bottle.setPrice(bottle.getSize());
                bottle_list.add(bottle);
            }
            else{}

        }
    }
    public static Dispenser getDispenser(){
        return disp;
    }

    public String addMoney(int money) {
        this.money += money;

        String s = "" +this.money;
        return s;
    }

    public double getBalance(){
        return this.money;
    }

    public void setBalance(double money){
        this.money += money;
    }

    public void buyBottle(int bottle) {
        String s = "";
        int index = bottle;
        double price = bottle_list.get(index).getPrice();
        if (money < price){

        }

        else if (bottle_list.get(index).getPrice() == 0.0){
        }
        else{
            bottles -= 1;

            removeBottle(index);

        }

    }


    public ArrayList getBottleList(){

        return bottle_list;

    }
    public void removeBottle(int bottle){
        bottle_list.get(bottle).setPrice(0.0);
    }

    public void cashOut(){
        this.money = 0;
    }
}