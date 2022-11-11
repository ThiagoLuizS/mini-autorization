package br.com.miniautorization.resource;

import br.com.miniautorization.models.dto.NewCardForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface TransactionResource {

    @PostMapping
    ResponseEntity<String> transactionCard(@Valid @RequestBody NewCardForm cardForm);

}
