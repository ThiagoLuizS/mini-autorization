package br.com.miniautorization.controller;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.models.dto.NewCardView;
import br.com.miniautorization.resource.CardResource;
import br.com.miniautorization.service.CardService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cartoes")
@Api(value = "Recursos para obter dados do cartão")
public class CardController implements CardResource {

    private final CardService cardService;

    @Override
    public ResponseEntity<BigDecimal> getBalanceCard(Long numberCard) {

        return null;
    }

    @Override
    public ResponseEntity<NewCardView> saveCard(NewCardForm cardForm) {
        Optional<NewCardView> newCardView = cardService.findByNumberCard(cardForm.getNumberCard());
        if(newCardView.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(newCardView.get());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.save(cardForm));
    }

}
