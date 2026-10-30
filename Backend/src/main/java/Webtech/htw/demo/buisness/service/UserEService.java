package Webtech.htw.demo.buisness.service;


import Webtech.htw.demo.persistence.entity.UserE;
import Webtech.htw.demo.persistence.repo.UserERepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEService {

    @Autowired
    UserERepo repo;

    public UserE save(UserE user){
        return repo.save(user);
    }

    public UserE get(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
