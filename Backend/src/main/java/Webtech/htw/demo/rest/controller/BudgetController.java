package Webtech.htw.demo.rest.controller;

import Webtech.htw.demo.buisness.service.BudgetEService;
import Webtech.htw.demo.persistence.entity.Budget;
import Webtech.htw.demo.rest.model.BudgetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BudgetController {

    @Autowired
    BudgetEService service;

    // GET /api/budget -> aktuelles Budget
    @GetMapping("/api/budget")
    public Budget getBudget() {
        return service.get();
    }

    // PUT /api/budget  Body: { "amount": 250.50 } -> Budget überschreiben
    @PutMapping("/api/budget")
    public Budget setBudget(@RequestBody BudgetDto dto) {
        return service.save(dto.getAmount());
    }

    // PUT /api/budget/ausgabe  Body: { "amount": 42.80 } -> Kosten abziehen
    @PutMapping("/api/budget/ausgabe")
    public Budget ausgabeAbziehen(@RequestBody BudgetDto dto) {
        return service.einkauf(dto.getAmount());
    }
}
