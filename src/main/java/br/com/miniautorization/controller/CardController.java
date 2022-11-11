package br.com.miniautorization.controller;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.models.dto.NewCardView;
import br.com.miniautorization.resource.CardResource;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cartoes")
@Api(value = "Recursos para obter dados do cartão")
public class CardController implements CardResource {

    @Override
    public BigDecimal getBalanceCard(Long numberCard) {
        return null;
    }

    @Override
    public NewCardView saveCard(NewCardForm cardForm) {
        return null;
    }

}
