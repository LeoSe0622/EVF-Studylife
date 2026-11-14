package Webtech.htw.demo.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ListenEintragE {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserE user;


    @ManyToOne
    @JoinColumn(name = "artikel_id")
    private ArtikelE artikel;

    private int menge;
    private String liste;

    public ListenEintragE() {}

    public void setArtikel(ArtikelE artikel) {
        this.artikel = artikel;
    }

    public void setUser(UserE user) {
        this.user = user;
    }


    public ListenEintragE(UserE user, ArtikelE artikel, int menge, String liste) {
        this.user = user;
        this.artikel = artikel;
        this.menge = menge;
        this.liste = liste;
    }

    public Long getId() { return id; }

    public UserE getUser() { return user; }

    public ArtikelE getArtikel() { return artikel; }

    public int getMenge() { return menge; }

    public void setMenge(int menge) { this.menge = menge; }

    public String getListe() { return liste; }

    public void setListe(String liste) { this.liste = liste; }
}
