package Webtech.htw.demo.buisness.service;

import Webtech.htw.demo.persistence.entity.ArtikelE;
import Webtech.htw.demo.persistence.entity.ListenEintragE;
import Webtech.htw.demo.persistence.repo.ArtikelERepo;
import Webtech.htw.demo.persistence.repo.ListenEintragERepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ListenEintragEService {
    @Autowired
    ListenEintragERepo repo;
    @Autowired
    ArtikelERepo artikelRepo;

    // Alle Einträge einer Liste holen ("EINKAUF" oder "STOCK")
    public List<ListenEintragE> getListe(String liste) {
        return repo.findByListe(liste);
    }

    // Artikel auf die Einkaufsliste setzen
    public ListenEintragE hinzufuegen(String name, int menge) {
        ArtikelE artikel = artikelRepo.save(new ArtikelE(name));

        // 3. Eintrag auf der Einkaufsliste anlegen
        ListenEintragE eintrag = new ListenEintragE(artikel, menge, "EINKAUF");
        return (ListenEintragE) repo.save(eintrag);
    }

    public void abhaken(Long eintragId) {
        ListenEintragE eintrag = (ListenEintragE) repo.findById(eintragId).get();

        eintrag.setListe("STOCK");
        repo.save(eintrag);

    }
}
