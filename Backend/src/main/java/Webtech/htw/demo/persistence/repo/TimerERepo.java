package Webtech.htw.demo.persistence.repo;

import Webtech.htw.demo.persistence.entity.TimerE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimerERepo extends CrudRepository<TimerE, Long>{}
//
