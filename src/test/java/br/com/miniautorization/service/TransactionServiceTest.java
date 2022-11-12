package br.com.miniautorization.service;

import br.com.miniautorization.models.dto.NewTransactionCardForm;
import br.com.miniautorization.models.entity.Card;
import br.com.miniautorization.models.enumerators.ResultTransactionCardEnum;
import br.com.miniautorization.repository.CardRepository;
import br.com.miniautorization.util.ConstantsDefaultValueUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionServiceTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionService transactionService;


    @Test
    public void checkTransactionWasSuccessful1() throws Exception {
        Card card = cardRepository.findTop1ByOrderByIdDesc();

        NewTransactionCardForm cardForm = NewTransactionCardForm.builder()
                .numberCard(card.getNumberCard())
                .passwordCard(card.getPasswordCard())
                .balanceCard(new BigDecimal(10))
                .build();

        Mono<ResultTransactionCardEnum> result = transactionService.resultAndValidTransaction(cardForm);

        StepVerifier.create(result)
                .expectNext(ResultTransactionCardEnum.OK)
                .verifyComplete();
    }

    @Test
    public void checkTransactionPasswordInvalid2() {
        Card card = cardRepository.findTop1ByOrderByIdDesc();

        NewTransactionCardForm cardForm = NewTransactionCardForm.builder()
                .numberCard(card.getNumberCard())
                .passwordCard(789735)
                .balanceCard(new BigDecimal(10))
                .build();

        Mono<ResultTransactionCardEnum> result = transactionService.resultAndValidTransaction(cardForm);

        StepVerifier.create(result)
                .expectNext(ResultTransactionCardEnum.INVALID_PASSWORD)
                .verifyComplete();
    }

    @Test
    public void checkTransactionNumberCardInvalid3() {
        NewTransactionCardForm cardForm = NewTransactionCardForm.builder()
                .numberCard(000000000L)
                .passwordCard(789735)
                .balanceCard(new BigDecimal(10))
                .build();

        Mono<ResultTransactionCardEnum> result = transactionService.resultAndValidTransaction(cardForm);

        StepVerifier.create(result)
                .expectNext(ResultTransactionCardEnum.NON_EXISTING_CARD)
                .verifyComplete();
    }

    @Test
    public void checkTransactionInsufficientFunds4() {
        boolean stop = true;
        
        Long numberCard = Long.parseLong(ConstantsDefaultValueUtils.generatedRandomNumber(10));
        Integer password = Integer.parseInt(ConstantsDefaultValueUtils.generatedRandomNumber(4));

        Card card = cardRepository.save(Card.builder()
                .numberCard(numberCard)
                .passwordCard(password)
                .balanceCard(ConstantsDefaultValueUtils.default_balance_value)
                .build());

        NewTransactionCardForm cardForm = NewTransactionCardForm.builder()
                .numberCard(card.getNumberCard())
                .passwordCard(card.getPasswordCard())
                .balanceCard(new BigDecimal(10))
                .build();

        while (stop) {
            Mono<ResultTransactionCardEnum> result = transactionService.resultAndValidTransaction(cardForm);
            if(result.block().isInsufficientFunds()) {
                StepVerifier.create(result)
                        .expectNext(ResultTransactionCardEnum.INSUFFICIENT_FUNDS)
                        .verifyComplete();
                stop = false;
            }
        }

    }
}
