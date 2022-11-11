package br.com.miniautorization.controller;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.models.dto.NewTransactionCardForm;
import br.com.miniautorization.models.enumerators.ResultTransactionCard;
import br.com.miniautorization.resource.TransactionResource;
import br.com.miniautorization.service.TransactionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacoes")
@Api(value = "Recursos para realizar uma transação")
public class TransactionController implements TransactionResource {

    private final TransactionService transactionService;
    @Override
    public ResponseEntity<String> transactionCard(NewTransactionCardForm newTransactionCardForm) {
        ResultTransactionCard result = transactionService.resultAndValidTransaction(newTransactionCardForm);
        if(result.getName().equals(ResultTransactionCard.OK)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result.getName());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result.getName());
    }
}
