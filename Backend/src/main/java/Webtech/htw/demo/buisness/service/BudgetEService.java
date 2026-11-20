package Webtech.htw.demo.buisness.service;

import htw.demo.persistence.entity.Budget;
import htw.demo.persistence.repo.BudgetRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetEService {

    private double amount = 0.0;
    private static final Long id = 1L;
    @Autowired
    BudgetRepository repo;

    // Budget holen. Gibt es noch keins, wird eins mit 0 angelegt.
    public Budget get() {
        Budget budget = repo.findById(id).orElse(null);

        if (budget == null) {
            budget = new Budget();
            budget.setId(id);
            budget.setAmount(0);
            budget = repo.save(budget);
        }

        amount = budget.getAmount();   // globale Variable aktualisieren
        return budget;
    }

    // Budget auf einen neuen Betrag setzen und speichern
    public Budget save(double neuerAmount) {
        if (neuerAmount < 0) {
            throw new IllegalArgumentException("Betrag darf nicht negativ sein");
        } else {
            amount = neuerAmount;
        }

        Budget budget = get();
        budget.setAmount(amount);
        return repo.save(budget);
    }

    public userEinkauf (){

    }

}