package Webtech.htw.demo.persistence.entity;

import jakarta.persistence.*;

@Entity
public class ListenEintragE {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artikel_id")
    private ArtikelE artikel;

    private int menge;
    private String liste;

    public ListenEintragE() {}

    public void setArtikel(ArtikelE artikel) {
        this.artikel = artikel;
    }

    public ListenEintragE(ArtikelE artikel, int menge, String liste) {
        this.artikel = artikel;
        this.menge = menge;
        this.liste = liste;
    }

    public Long getId() { return id; }

    public ArtikelE getArtikel() { return artikel; }

    public int getMenge() { return menge; }

    public void setMenge(int menge) { this.menge = menge; }

    public String getListe() { return liste; }

    public void setListe(String liste) { this.liste = liste; }
}
