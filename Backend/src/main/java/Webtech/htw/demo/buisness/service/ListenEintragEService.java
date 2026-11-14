package Webtech.htw.demo.buisness.service;

import Webtech.htw.demo.persistence.entity.ArtikelE;
import Webtech.htw.demo.persistence.entity.ListenEintragE;
import Webtech.htw.demo.persistence.entity.UserE;
import Webtech.htw.demo.persistence.repo.ArtikelERepo;
import Webtech.htw.demo.persistence.repo.ListenEintragERepo;
import Webtech.htw.demo.persistence.repo.UserERepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ListenEintragEService {
    @Autowired
    ListenEintragERepo repo;
    @Autowired
    ArtikelERepo artikelRepo;
    @Autowired
    UserERepo userRepo;

    // Alle Einträge einer Liste holen ("EINKAUF" oder "STOCK")
    public List<ListenEintragE> getListe(Long userId, String liste) {
        return repo.findByUserIdAndListe(userId, liste);
    }

    // Artikel auf die Einkaufsliste setzen
    public ListenEintragE hinzufuegen(Long userId, String name, int menge) {
        UserE user = userRepo.findById(userId).get();

        ArtikelE artikel = artikelRepo.save(new ArtikelE(name));

        // 3. Eintrag auf der Einkaufsliste anlegen
        ListenEintragE eintrag = new ListenEintragE(user, artikel, menge, "EINKAUF");
        return (ListenEintragE) repo.save(eintrag);
    }

    public void abhaken(Long eintragId) {
        ListenEintragE eintrag = (ListenEintragE) repo.findById(eintragId).get();

        eintrag.setListe("STOCK");
        repo.save(eintrag);

    }
}
