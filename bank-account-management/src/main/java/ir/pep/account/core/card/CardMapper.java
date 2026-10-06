package ir.pep.account.core.card;

import jakarta.inject.Singleton;

import java.time.Instant;

@Singleton
public class CardMapper {

    public CardResponseDto toDto(Card card) {
        CardResponseDto response = new CardResponseDto();
        response.setId(card.getId());
        response.setCardNumber(card.getCardNumber());
        response.setEnabled(card.isEnabled());
        response.setCreatedAt(card.getCreatedAt());
        return response;
    }

    public Card toModel(CardRequestDto request){
        Card card = new Card();
        card.setCardNumber(request.getCardNumber());
        card.setEnabled(request.isEnabled());
        card.setCreatedAt(Instant.now());

        return card;
    }
}
