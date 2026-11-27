package Webtech.htw.demo.rest.model;

public class FokusDTO {

    private String text;

    public FokusDTO() {}   // braucht Jackson zum Einlesen

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
