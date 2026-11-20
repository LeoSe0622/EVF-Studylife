package Webtech.htw.demo.rest.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BudgetDto {
        private double amount;

        public BudgetDto() {}   // braucht Jackson zum Einlesen

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
}
