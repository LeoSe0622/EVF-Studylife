package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.buisness.service.ListenEintragEService;
import Webtech.htw.demo.persistence.entity.ListenEintragE;
import Webtech.htw.demo.rest.model.EintragDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListenEintragEController {

    @Autowired
    ListenEintragEService service;

    @GetMapping("/user/{id}/einkaufsliste")
    public List<ListenEintragE> getEinkaufsliste(@PathVariable Long id) {
        return service.getListe(id, "EINKAUF");
    }

    @GetMapping("/user/{id}/stockliste")
    public List<ListenEintragE> getStockliste(@PathVariable Long id) {
        return service.getListe(id, "STOCK");
    }

    @PostMapping("/user/{id}/einkaufsliste")
    public ListenEintragE hinzufuegen(@PathVariable Long id, @RequestBody EintragDTO dto) {
        return service.hinzufuegen(id, dto.getName(), dto.getMenge());
    }

    @PutMapping("/eintrag/{id}/abhaken")
    public void abhaken(@PathVariable Long id) {
        service.abhaken(id);
    }
}
