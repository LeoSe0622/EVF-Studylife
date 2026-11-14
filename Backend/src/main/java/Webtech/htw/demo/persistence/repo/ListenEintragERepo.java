package Webtech.htw.demo.persistence.repo;

import org.springframework.data.repository.CrudRepository;
import Webtech.htw.demo.persistence.entity.ListenEintragE;

import java.util.List;

public interface ListenEintragERepo extends CrudRepository<ListenEintragE, Long> {
    List<ListenEintragE> findByUserIdAndListe(Long userId, String liste);
}
