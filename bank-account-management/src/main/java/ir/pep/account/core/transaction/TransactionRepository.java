package ir.pep.account.core.transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    void insert(Transaction transaction);
    Optional<Transaction> get(String id);
    List<Transaction> getAll();
    List<Transaction> getCardId(String cardId);
    List<Transaction> getCardNumber(String cardNumber);
    public List<Transaction> getSuccessfulTransactions(String cardId);
    List<TransactionSummeryDto> getTransactionSummeries(String cardNumber);
}
