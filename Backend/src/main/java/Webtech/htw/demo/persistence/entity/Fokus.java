package Webtech.htw.demo.persistence.entity;

import jakarta.persistence.*;

@Entity
public class Fokus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private boolean erledigt = false;

    public Fokus() {}

    public Fokus(String text) {
        this.text = text;
    }

    public Long getId() { return id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public boolean isErledigt() { return erledigt; }

    public void setErledigt(boolean erledigt) { this.erledigt = erledigt; }
}
