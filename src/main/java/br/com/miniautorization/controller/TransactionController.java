package br.com.miniautorization.controller;

import br.com.miniautorization.models.dto.NewTransactionCardForm;
import br.com.miniautorization.models.enumerators.ResultTransactionCardEnum;
import br.com.miniautorization.resource.TransactionResource;
import br.com.miniautorization.service.TransactionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacoes")
@Api(value = "Recursos para realizar uma transação")
public class TransactionController implements TransactionResource {

    private final TransactionService transactionService;
    @Override
    public Mono<ResponseEntity<String>> transactionCard(NewTransactionCardForm newTransactionCardForm) {
        Mono<ResultTransactionCardEnum> result = transactionService.resultAndValidTransaction(newTransactionCardForm);
        if(result.block().getName().equals(ResultTransactionCardEnum.OK.getName())) {
            return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(result.block().getName()));
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result.block().getName()));
    }
}
