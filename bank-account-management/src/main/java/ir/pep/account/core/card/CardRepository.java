package ir.pep.account.core.card;

import java.util.List;
import java.util.Optional;


public interface CardRepository {
    void insert(Card card);
    Optional<Card> getId(String id);
    List<Card> get(List<String> ids);
    List<Card> getAll();
    List<Card> getCards(List<String> cardNumber);
    void update(Card card);
    void delete(String id);
    void updateCardNumber(String id , String cardNumbers);
    void updateStatus(String id, boolean enabled);
}
