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

    @Get("/active")
    public HttpResponse<List<CardResponseDto>> getAllActive(){
        List<CardResponseDto> activeCards = cardService.getAllActive();
        return HttpResponse.ok(activeCards);
    }

    @Put("/{id}")
    public HttpResponse<Void> update(@PathVariable String id , @Body CardRequestDto dto){
        dto.setId(id);
        cardService.update(dto);
        return HttpResponse.noContent();
    }

    @Patch("/{id}/status")
    public HttpResponse<Void> updateStatus(@PathVariable String id , @QueryValue boolean enabled){
        cardService.updateStatus(id, enabled);
        return HttpResponse.noContent();
    }

    @Patch("/{id}/card-number")
    public HttpResponse<Void> updateCardNumber(@PathVariable String id,@QueryValue String cardNumber){
        cardService.updateCardNumber(id, cardNumber);
        return HttpResponse.noContent();
    }



    @Delete("/{id}")
    public HttpResponse<Void> delete(@PathVariable String id){
        cardService.delete(id);
        return HttpResponse.noContent();
    }
}
