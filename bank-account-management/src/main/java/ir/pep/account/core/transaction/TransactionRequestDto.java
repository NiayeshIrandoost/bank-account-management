package ir.pep.account.core.transaction;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable
@Introspected
public class TransactionRequestDto {
    private String cardId;
    private BigDecimal amount;
    private String type;

    public TransactionRequestDto() {
    }

    public TransactionRequestDto(String cardId, BigDecimal amount, String type) {
        this.cardId = cardId;
        this.amount = amount;
        this.type = type;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
