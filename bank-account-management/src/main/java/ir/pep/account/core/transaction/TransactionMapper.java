package ir.pep.account.core.transaction;

import ir.pep.account.core.card.Card;
import jakarta.inject.Singleton;
import java.time.Instant;
import java.util.UUID;

@Singleton
public class TransactionMapper {
    public TransactionResponseDto toDto(Transaction transaction) {
        TransactionResponseDto response = new TransactionResponseDto();
        response.setId(transaction.getId());
        response.setCard(transaction.getCard());
        response.setAmount(transaction.getAmount());
        response.setType(transaction.getType());
        response.setTime(transaction.getTime());
        response.setStatus(transaction.getStatus());
        response.setEnabled(transaction.isEnabled());
        response.setCreatedAt(transaction.getCreatedAt());
        return response;
    }

    public Transaction toModel(TransactionRequestDto request, Card card) {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setCard(card);
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setTime(Instant.now());
        transaction.setStatus("SUCCESS");
        transaction.setEnabled(true);
        transaction.setCreatedAt(Instant.now());
        return transaction;
    }

}
