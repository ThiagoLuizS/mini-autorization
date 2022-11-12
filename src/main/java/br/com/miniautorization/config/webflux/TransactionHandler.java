package br.com.miniautorization.config.webflux;

import br.com.miniautorization.models.dto.NewTransactionCardForm;
import br.com.miniautorization.models.enumerators.ResultTransactionCard;
import br.com.miniautorization.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransactionHandler {

    private final TransactionService transactionService;

    public Mono<ResponseEntity<String>> transactionCard(ServerRequest request) {
        Mono<NewTransactionCardForm> form = request.bodyToMono(NewTransactionCardForm.class);

        Mono<ResultTransactionCard> result = transactionService.resultAndValidTransaction(form.block());
        if(result.block().getName().equals(ResultTransactionCard.OK)) {
            return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(result.block().getName()));
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result.block().getName()));
    }
}
