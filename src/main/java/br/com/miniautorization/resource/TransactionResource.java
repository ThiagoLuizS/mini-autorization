package br.com.miniautorization.resource;

import br.com.miniautorization.models.dto.NewTransactionCardForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface TransactionResource {

    @PostMapping
    @ApiOperation(value = "Recurso responsavel por realizar uma transação")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Atributos do corpo da requisição podem está vazios"),
            @ApiResponse(code = 422, message = "Alguma excessão da regra"),
            @ApiResponse(code = 201, message = "Requisição feita com sucesso")
    })
    Mono<ResponseEntity<String>> transactionCard(@Valid @RequestBody NewTransactionCardForm newTransactionCardForm);

}
