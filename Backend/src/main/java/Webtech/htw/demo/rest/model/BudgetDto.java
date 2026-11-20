package Webtech.htw.demo.rest.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BudgetDto(
        @NotNull(message = "Betrag fehlt")
        @DecimalMin(value = "0.00", message = "Budget darf nicht negativ sein")
        @Digits(integer = 10, fraction = 2, message = "Max. 10 Vorkomma- und 2 Nachkommastellen")
        BigDecimal amount
) {
}

