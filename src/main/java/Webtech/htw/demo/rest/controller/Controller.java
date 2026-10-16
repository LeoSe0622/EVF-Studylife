package Webtech.htw.demo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class Controller {

    @GetMapping("/")
    public List<GList> index() {
        return List.of(new GList("Milch", 1), new GList("Hühnchen", 4),new GList("Reis", 3),new GList("Honig", 1));
    }

    }
