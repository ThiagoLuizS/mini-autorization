package br.com.miniautorization.resource;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.models.dto.NewCardView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.math.BigDecimal;

public interface CardResource {

    @GetMapping("/{numberCard}")
    @ApiOperation(value = "Recurso responsavel por obter o saldo do cartão")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Atributos do corpo da requisição podem está vazios"),
            @ApiResponse(code = 404, message = "Cartão não existe"),
            @ApiResponse(code = 200, message = "Requisição feita com sucesso")
    })
    ResponseEntity<BigDecimal> getBalanceCard(@PathVariable(name = "numberCard") Long numberCard);

    @PostMapping
    @ApiOperation(value = "Recurso responsavel por salvar um novo cartão")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Atributos do corpo da requisição podem está vazios"),
            @ApiResponse(code = 422, message = "Já existe um cartão com esse número"),
            @ApiResponse(code = 201, message = "Requisição feita com sucesso")
    })
    ResponseEntity<NewCardView> saveCard(@Valid @RequestBody NewCardForm cardForm);

}
