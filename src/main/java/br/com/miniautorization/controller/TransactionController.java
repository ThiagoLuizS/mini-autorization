package br.com.miniautorization.controller;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.resource.TransactionResource;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacoes")
@Api(value = "Recursos para realizar uma transação")
public class TransactionController implements TransactionResource {
    @Override
    public String transactionCard(NewCardForm cardForm) {
        return null;
    }
}
