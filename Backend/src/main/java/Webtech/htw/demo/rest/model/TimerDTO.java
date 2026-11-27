package Webtech.htw.demo.rest.model;

public class TimerDTO {

    private String name;
    private int minuten;

    public TimerDTO() {}   // braucht Jackson zum Einlesen

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getMinuten() { return minuten; }
    public void setMinuten(int minuten) { this.minuten = minuten; }
    //
}
