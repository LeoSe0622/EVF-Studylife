package Webtech.htw.demo.rest.model;

public class EintragDTO {

    private String name;
    private int menge;

    public EintragDTO() {}   // braucht Jackson zum Einlesen

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getMenge() { return menge; }
    public void setMenge(int menge) { this.menge = menge; }
}
