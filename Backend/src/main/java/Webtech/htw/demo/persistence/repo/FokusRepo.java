package Webtech.htw.demo.persistence.repo;

import Webtech.htw.demo.persistence.entity.Fokus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface FokusRepo extends CrudRepository<Fokus, Long>{}
