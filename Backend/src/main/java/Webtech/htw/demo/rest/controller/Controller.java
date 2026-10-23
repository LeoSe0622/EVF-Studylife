package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.rest.model.ArtikelDTO;
import Webtech.htw.demo.rest.model.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class Controller {

    @GetMapping("/data")
    public List<ArtikelDTO> index() {
        ArtikelDTO u1 = new ArtikelDTO("Leo");
        return List.of(u1);
    }

    }
