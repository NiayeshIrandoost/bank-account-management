package ir.pep.account.core.transaction;


import jakarta.inject.Singleton;

import java.time.Instant;
import java.util.UUID;


@Singleton
public class TransactionMapper {

    public TransactionResponseDto toResponse(Transaction transaction) {
        TransactionResponseDto response = new TransactionResponseDto();
        response.setId(transaction.getId());
        response.setCard(transaction.getCard());
        response.setAmount(transaction.getAmount());
        response.setTime(transaction.getTime());
        response.setStatus(transaction.getStatus());
        response.setEnabled(transaction.isEnabled());
        response.setCreatedAt(transaction.getCreatedAt());
        return response;
    }


    public Transaction toEntity(TransactionRequestDto request){
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setCard(request.getCard());
        transaction.setAmount(request.getAmount());
        transaction.setTime(request.getTime());
        transaction.setStatus(request.getStatus());
        transaction.setEnabled(request.isEnabled());
        transaction.setCreatedAt(Instant.now());
        return transaction;
    }
}
