package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.rest.model.ArtikelDTO;
import Webtech.htw.demo.rest.model.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class Controller {

    @GetMapping("/")
    public List<UserDTO> index() {
        UserDTO u1 = new UserDTO("Leo", "Leo123@", "123456789");
        return List.of(u1);
    }

    }
