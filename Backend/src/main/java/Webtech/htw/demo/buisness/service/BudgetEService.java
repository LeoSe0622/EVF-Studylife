package Webtech.htw.demo.buisness.service;

import Webtech.htw.demo.persistence.entity.Budget;
import Webtech.htw.demo.persistence.repo.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetEService {

    private static final Long id = 1L;
    @Autowired
    BudgetRepository repo;

    // Budget holen. Gibt es noch keins, wird eins mit 0 angelegt.
    public Budget get() {
        Budget budget = repo.findById(id).orElse(null);

        if (budget == null) {
            budget = new Budget(id);
            budget.setAmount(0);
            budget = repo.save(budget);
        }
        return budget;
    }

    // Budget auf einen neuen Betrag setzen und speichern
    public Budget save(double neuerAmount) {
        if (neuerAmount < 0) {
            throw new IllegalArgumentException("Betrag darf nicht negativ sein");
        }

        Budget budget = get();
        budget.setAmount(neuerAmount);
        return repo.save(budget);
    }

    // Kosten eines Einkaufs vom Budget abziehen
    public Budget einkauf (double userAmount){
        Budget budget = get();
        budget.setAmount(budget.getAmount() - userAmount);
        return repo.save(budget);

    }
}