package Webtech.htw.demo.persistence.repo;

import Webtech.htw.demo.persistence.entity.ArtikelE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface ArtikelERepo extends CrudRepository<ArtikelE, Long>{}
