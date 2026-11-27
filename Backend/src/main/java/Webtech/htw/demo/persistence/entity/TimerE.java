package Webtech.htw.demo.persistence.entity;

import jakarta.persistence.*;

@Entity
public class TimerE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int minuten;

    public TimerE() {}

    public TimerE(String name, int minuten) {
        this.name = name;
        this.minuten = minuten;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getMinuten() { return minuten; }

    public void setMinuten(int minuten) { this.minuten = minuten; }
    //
}
