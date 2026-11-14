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

    @GetMapping("/einkaufsliste")
    public List<ListenEintragE> getEinkaufsliste() {
        return service.getListe("EINKAUF");
    }

    @GetMapping("/stockliste")
    public List<ListenEintragE> getStockliste() {
        return service.getListe("STOCK");
    }

    @PostMapping("/einkaufsliste")
    public ListenEintragE hinzufuegen(@RequestBody EintragDTO dto) {
        return service.hinzufuegen(dto.getName(), dto.getMenge());
    }

    @PutMapping("/eintrag/{id}/abhaken")
    public void abhaken(@PathVariable Long id) {
        service.abhaken(id);
    }
}
