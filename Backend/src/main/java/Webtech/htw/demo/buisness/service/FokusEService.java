package Webtech.htw.demo.buisness.service;

import Webtech.htw.demo.persistence.entity.Fokus;
import Webtech.htw.demo.persistence.entity.TimerE;
import Webtech.htw.demo.persistence.repo.FokusRepo;
import Webtech.htw.demo.persistence.repo.TimerERepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FokusEService {
    @Autowired
    FokusRepo repo;
    @Autowired
    TimerERepo timerRepo;

    // ---------- To-Dos ----------

    // Alle To-Do-Einträge holen
    public List<Fokus> getAllTodos() {
        List<Fokus> alle = new ArrayList<>();
        for (Fokus todo : repo.findAll()) {
            alle.add(todo);
        }
        return alle;
    }

    // Neuen einzelnen To-Do-Eintrag anlegen
    public Fokus todoHinzufuegen(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("To-Do darf nicht leer sein");
        }
        return repo.save(new Fokus(text));
    }

    // Eintrag abhaken bzw. wieder auf offen setzen
    public Fokus todoAbhaken(Long id) {
        Fokus todo = repo.findById(id).orElseThrow(() -> new RuntimeException());

        todo.setErledigt(!todo.isErledigt());
        return repo.save(todo);
    }

    // Eintrag löschen
    public void todoLoeschen(Long id) {
        repo.deleteById(id);
    }

    // ---------- Timer ----------

    // Alle gespeicherten Timer holen
    public List<TimerE> getAllTimer() {
        List<TimerE> alle = new ArrayList<>();
        for (TimerE timer : timerRepo.findAll()) {
            alle.add(timer);
        }
        return alle;
    }

    // Neuen Timer festlegen
    public TimerE timerHinzufuegen(String name, int minuten) {
        if (minuten <= 0) {
            throw new IllegalArgumentException("Minuten müssen größer als 0 sein");
        }
        return timerRepo.save(new TimerE(name, minuten));
    }

    // Timer löschen
    public void timerLoeschen(Long id) {
        timerRepo.deleteById(id);
    }
}
