package ir.pep.account.core.transaction;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import ir.pep.account.core.card.Card;

import java.math.BigDecimal;
import java.time.Instant;


@Serdeable
@Introspected
public class Transaction {

    private String id;
    private Card card;
    private BigDecimal amount;
    private Instant time;
    private String status;
    private boolean enabled;
    private Instant createdAt;


    public Transaction(String id, Card card, BigDecimal amount, Instant time, String status, boolean enabled, Instant createdAt) {
        this.id = id;
        this.card = card;
        this.amount = amount;
        this.time = time;
        this.status = status;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public Transaction() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

