package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.buisness.service.ArtikelEService;
import Webtech.htw.demo.persistence.entity.ArtikelE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtikelEController {
    @Autowired
    ArtikelEService service;

    @PostMapping("/ArtikelE")
    public ArtikelE createThing(@RequestBody ArtikelE artikel){
        return service.save(artikel);
    }

    @GetMapping("/artikel/{id}")
    public ArtikelE getArtikelE(@PathVariable String id){
        Long artikelId = Long.parseLong(id);
        return service.get(artikelId);
    }
    @GetMapping("/artikel")
    public List<ArtikelE> getAllArtikelE(){
        return service.getAll();
    }
}
