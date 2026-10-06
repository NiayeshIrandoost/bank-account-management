package ir.pep.account.core.card;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.Instant;

@Serdeable
@Introspected
public class CardResponseDto {
    private String id;
    private String cardNumber;
    private boolean enabled;
    private Instant createdAt;

    public CardResponseDto() {}
    public CardResponseDto(String id, String cardNumber, boolean enabled, Instant createdAt) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}