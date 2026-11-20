package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.persistence.entity.Budget;
import Webtech.htw.demo.persistence.repo.BudgetRepository;
import Webtech.htw.demo.rest.model.BudgetDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;



@RestController
public class BudgetController {

    private final BudgetRepository budgetRepository;

    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    // GET /api/budget -> aktuelles Budget, oder 0.00 falls noch keins angelegt wurde
    @GetMapping ("/api/budget")
    public BudgetDto getBudget() {
        Long userId = currentUserId();

        BigDecimal amount = budgetRepository.findById(userId)
                .map(Budget::getAmount)
                .orElse(BigDecimal.ZERO);

        return new BudgetDto(amount);
    }

    // PUT /api/budget  Body: { "amount": 250.50 }
    // Legt das Budget an oder überschreibt es, falls schon eins existiert
    @PutMapping("/api/budget")
    public BudgetDto setBudget(@Valid @RequestBody BudgetDto request) {
        Long userId = currentUserId();

        Budget budget = budgetRepository.findById(userId)
                .orElseGet(() -> new Budget(userId));
        budget.setAmount(request.amount());

        Budget saved = budgetRepository.save(budget);
        return new BudgetDto(saved.getAmount());
    }

    /**
     * TODO: Durch den echten eingeloggten User ersetzen, z. B. über Spring Security
     * (Authentication / @AuthenticationPrincipal). Die User-ID sollte nie
     * aus dem Request-Body oder einem Parameter kommen, sonst könnte jeder
     * fremde Budgets ändern. Bis dahin: fester Testwert.
     */
    private Long currentUserId() {
        return 1L;
    }
}