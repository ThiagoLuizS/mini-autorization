package br.com.miniautorization.resource;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.models.dto.NewCardView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.math.BigDecimal;

public interface CardResource {

    @GetMapping("/{numberCard}")
    ResponseEntity<BigDecimal> getBalanceCard(@PathVariable(name = "numberCard") Long numberCard);

    @PostMapping
    ResponseEntity<NewCardView> saveCard(@Valid @RequestBody NewCardForm cardForm);

}
