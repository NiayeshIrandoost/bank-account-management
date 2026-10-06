package ir.pep.account.app.card;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import ir.pep.account.core.card.CardRequestDto;
import ir.pep.account.core.card.CardResponseDto;
import ir.pep.account.core.card.CardService;

import java.util.List;

@Controller("/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Post
    public HttpResponse<Void> save(@Body CardRequestDto dto){
        cardService.create(dto);
        return HttpResponse.status(HttpStatus.OK);
    }

    @Get
    public HttpResponse<List<CardResponseDto>> getAll(){
        List<CardResponseDto> cards = cardService.getAll();
        return HttpResponse.ok(cards);
    }

    @Get("/{id}")
    public HttpResponse<CardResponseDto> get(@PathVariable String id){
        CardResponseDto card = cardService.getById(id);
        return HttpResponse.ok(card);
    }

    @Delete("/{id}")
    public HttpResponse<Void> delete(@PathVariable String id){
        cardService.delete(id);
        return HttpResponse.noContent();
    }
}
