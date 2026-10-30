package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.buisness.service.UserEService;
import Webtech.htw.demo.persistence.entity.UserE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserEController {
    @Autowired
    UserEService service;

    @PostMapping("/UserE")
    public UserE createUserE(@RequestBody UserE user){
        return service.save(user);
    }

    @GetMapping("/user/{id}")
    public UserE getUserE(@PathVariable String id){
        Long userId = Long.parseLong(id);
        return service.get(userId);
    }
}
