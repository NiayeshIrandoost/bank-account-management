package ir.pep.account.core.card;

import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;


@Singleton
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Transactional
    public void create(CardRequestDto dto) {
        Card card = cardMapper.toModel(dto);

            card.setId(UUID.randomUUID().toString());
            card.setEnabled(true);
        cardRepository.insert(card);
    }

    public List<CardResponseDto> getAll() {
        return cardRepository.getAll()
                .stream()
                .map(cardMapper::toDto)
                .toList();
    }

    public List<CardResponseDto> getAllActive() {
        return cardRepository.getAll()
                .stream()
                .filter(Card::isEnabled)
                .map(cardMapper::toDto)
                .toList();
    }

    public CardResponseDto getById(String id) {
        return cardRepository.getId(id)
                .map(cardMapper::toDto)
                .orElseThrow(() -> new RuntimeException("card with id " + id + " not found."));
    }

    public List<CardResponseDto> getByIds(List<String> ids) {
        return cardRepository.get(ids)
                .stream()
                .map(cardMapper::toDto)
                .toList();
    }

    public List<CardResponseDto> getByCardNumbers(List<String> cardNumbers) {
        List<Card> cards = cardRepository.getCards(cardNumbers);
        return cards.stream()
                .map(cardMapper::toDto)
                .toList();
    }


    @Transactional
    public void update(CardRequestDto dto) {
        cardRepository.getId(dto.getId())
                .orElseThrow(() -> new RuntimeException("card with id " + dto.getId() + " not found."));

        Card card = cardMapper.toModel(dto);
        cardRepository.update(card);
    }

    @Transactional
    public void updateStatus(String id, boolean enabled) {
        cardRepository.getId(id)
                .orElseThrow(() -> new RuntimeException("card with id " + id + " not found."));

        cardRepository.updateStatus(id, enabled);
    }

    @Transactional
    public void updateCardNumber(String id, String cardNumber) {
        cardRepository.getId(id)
                .orElseThrow(() -> new RuntimeException("card with id " + id + " not found."));
        cardRepository.updateCardNumber(id, cardNumber);
    }

    @Transactional
    public void delete(String id) {
        cardRepository.delete(id);
    }

}
