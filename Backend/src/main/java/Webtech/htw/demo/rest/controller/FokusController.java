package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.buisness.service.FokusEService;
import Webtech.htw.demo.persistence.entity.Fokus;
import Webtech.htw.demo.persistence.entity.TimerE;
import Webtech.htw.demo.rest.model.FokusDTO;
import Webtech.htw.demo.rest.model.TimerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FokusController {

    @Autowired
    FokusEService service;

    // GET /fokus/todos -> alle To-Dos
    @GetMapping("/fokus/todos")
    public List<Fokus> getTodos() {
        return service.getAllTodos();
    }

    // POST /fokus/todos  Body: { "text": "Mathe Blatt 3" } -> neuer Eintrag
    @PostMapping("/fokus/todos")
    public Fokus todoHinzufuegen(@RequestBody FokusDTO dto) {
        return service.todoHinzufuegen(dto.getText());
    }

    // PUT /fokus/todos/{id}/abhaken -> erledigt umschalten
    @PutMapping("/fokus/todos/{id}/abhaken")
    public Fokus todoAbhaken(@PathVariable Long id) {
        return service.todoAbhaken(id);
    }

    // DELETE /fokus/todos/{id} -> Eintrag löschen
    @DeleteMapping("/fokus/todos/{id}")
    public void todoLoeschen(@PathVariable Long id) {
        service.todoLoeschen(id);
    }

    // GET /fokus/timer -> alle Timer
    @GetMapping("/fokus/timer")
    public List<TimerE> getTimer() {
        return service.getAllTimer();
    }

    // POST /fokus/timer  Body: { "name": "Lernen", "minuten": 25 } -> neuer Timer
    @PostMapping("/fokus/timer")
    public TimerE timerHinzufuegen(@RequestBody TimerDTO dto) {
        return service.timerHinzufuegen(dto.getName(), dto.getMinuten());
    }

    // DELETE /fokus/timer/{id} -> Timer löschen
    @DeleteMapping("/fokus/timer/{id}")
    public void timerLoeschen(@PathVariable Long id) {
        service.timerLoeschen(id);
    }
}
