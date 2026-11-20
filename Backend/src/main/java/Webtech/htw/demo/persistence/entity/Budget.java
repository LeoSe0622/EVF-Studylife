package Webtech.htw.demo.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
public class Budget {

    // Jeder User hat genau ein Budget -> user_id ist gleichzeitig der Primary Key
    @Id
    private Long userId;

    // BigDecimal statt double: keine Rundungsfehler bei Geldbeträgen
    private BigDecimal amount = BigDecimal.ZERO;

    private Budget() {
        // von JPA benötigt
    }

    public Budget(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

