package ir.pep.account.app.transaction;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import ir.pep.account.core.transaction.TransactionRequestDto;
import ir.pep.account.core.transaction.TransactionResponseDto;
import ir.pep.account.core.transaction.TransactionService;
import ir.pep.account.core.transaction.TransactionSummeryDto;

import java.util.List;
import java.util.Map;

@Controller("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Post
    public HttpResponse<Void> save(@Body TransactionRequestDto dto) {
        transactionService.create(dto);
        return HttpResponse.status(HttpStatus.OK);
    }

    @Get
    public HttpResponse<List<TransactionResponseDto>> getAll() {
        List<TransactionResponseDto> response = transactionService.getAll();
        return HttpResponse.ok(response);
    }


    @Get("/{id}")
    public HttpResponse<TransactionResponseDto> get(@PathVariable String id) {
        TransactionResponseDto response = transactionService.get(id);
        return HttpResponse.ok(response);
    }



    @Get("/type")
    public HttpResponse<Map<String, List<TransactionResponseDto>>> getGroupedByType() {
        Map<String, List<TransactionResponseDto>> result = transactionService.getType();
        return HttpResponse.ok(result);
    }


    @Get("/card/{cardId}")
    public HttpResponse<List<TransactionResponseDto>> getCardId(@PathVariable String cardId) {
        List<TransactionResponseDto> transactions = transactionService.getCardId(cardId);
        return HttpResponse.ok(transactions);
    }

    @Get("/card-number")
    public HttpResponse<List<TransactionResponseDto>> getCardNumber(@QueryValue String cardNumber) {
        List<TransactionResponseDto> transactions = transactionService.getCardNumber(cardNumber);
        return HttpResponse.ok(transactions);
    }

    @Get("/summary")
    public HttpResponse<List<TransactionSummeryDto>> getSummaries(@QueryValue String cardNumber) {
        List<TransactionSummeryDto> summaries = transactionService.getTransactionSummeries(cardNumber);
        return HttpResponse.ok(summaries);
    }

    @Get("/card/{cardId}/successful")
    public HttpResponse<List<TransactionResponseDto>> getSuccessfulTransactions(@PathVariable String cardId) {
        List<TransactionResponseDto> transactions = transactionService.getSuccessfulTransactions(cardId);
        return HttpResponse.ok(transactions);
    }
}
