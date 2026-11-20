package Webtech.htw.demo.persistence.repo;

import Webtech.htw.demo.persistence.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}