package Webtech.htw.demo.buisness.service;


import Webtech.htw.demo.persistence.entity.ArtikelE;
import Webtech.htw.demo.persistence.repo.ArtikelERepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtikelEService {

    @Autowired
    ArtikelERepo repo;

    public ArtikelE save(ArtikelE artikel){
        return repo.save(artikel);
    }

    public ArtikelE get(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
