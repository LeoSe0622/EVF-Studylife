package Webtech.htw.demo;

public class SList {
    private String name;
    private boolean stillThere;
    private int anzahl;

    public void SList(String name, boolean stillThere, int anzahl){
        this.name = name;
        this.stillThere = stillThere;
        this.anzahl = anzahl;
    }

    public String getName(){
        return this.name;
    }

    public void used(int amount){
        this.anzahl = anzahl - amount;
    }

    public void usedUp(){
        this.stillThere= false;
    }
}
