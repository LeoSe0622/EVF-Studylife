package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.rest.model.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class Controller {

    @GetMapping("/")
    public List<UserDTO> index() {
        return List.of(new GList("Milch", 1), new GList("Hühnchen", 4),new GList("Reis", 3),new GList("Honig", 1));
    }

    }
