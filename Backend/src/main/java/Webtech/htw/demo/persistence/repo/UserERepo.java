package Webtech.htw.demo.persistence.repo;

import Webtech.htw.demo.persistence.entity.UserE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface UserERepo extends CrudRepository<UserE, Long>{}
