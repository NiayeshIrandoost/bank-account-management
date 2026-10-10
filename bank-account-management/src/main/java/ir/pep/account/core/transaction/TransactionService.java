package ir.pep.account.core.transaction;

import ir.pep.account.core.card.Card;
import ir.pep.account.core.card.CardRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Singleton
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CardRepository cardRepository;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.cardRepository = cardRepository;
    }

    @Transactional
    public void create(TransactionRequestDto dto) {
        if (dto.getCardId() == null || dto.getCardId().isBlank()) {
            throw new IllegalArgumentException("Card id must not be null or empty");
        }

        Card card = cardRepository.getId(dto.getCardId())
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + dto.getCardId()));

        if (!card.isEnabled()) {
            throw new IllegalStateException("Card is disabled: " + dto.getCardId());
        }
        Transaction transaction = transactionMapper.toModel(dto, card);
        transactionRepository.insert(transaction);
    }

    public List<TransactionResponseDto> getAll() {
        return transactionRepository.getAll()
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    public TransactionResponseDto get(String id) {
        return transactionRepository.get(id)
                .map(transactionMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id:" + id));
    }

    public List<TransactionResponseDto> getCardId(String cardId) {
        cardRepository.getId(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found with id:" + cardId));
        return transactionRepository.getCardId(cardId)
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    public List<TransactionResponseDto> getCardNumber(String cardNumber) {
        return transactionRepository.getCardNumber(cardNumber)
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    public Map<String, List<TransactionResponseDto>> getType() {
        return transactionRepository.getAll()
                .stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.groupingBy(dto ->
                        dto.getType() != null ? dto.getType() : "UNKNOWN"
                ));
    }

    public List<TransactionSummeryDto> getTransactionSummeries(String cardNumber) {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Card number must not be null or empty");
        }
        return transactionRepository.getTransactionSummeries(cardNumber);
    }

    public List<TransactionResponseDto> getSuccessfulTransactions(String cardId) {
        if (cardId == null || cardId.isBlank()) {
            throw new IllegalArgumentException("Card id must not be null or blank");
        }
        cardRepository.getId(cardId).orElseThrow(() -> new RuntimeException("Card not found with id :" + cardId));

        return transactionRepository.getSuccessfulTransactions(cardId).stream()
                .map(transactionMapper::toDto)
                .toList();
    }

}
