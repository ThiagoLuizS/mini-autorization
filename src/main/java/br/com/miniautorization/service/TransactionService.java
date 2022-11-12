package br.com.miniautorization.service;

import br.com.miniautorization.models.dto.NewTransactionCardForm;
import br.com.miniautorization.models.entity.Card;
import br.com.miniautorization.models.enumerators.ResultTransactionCardEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CardService cardService;

    public Mono<ResultTransactionCardEnum> resultAndValidTransaction(NewTransactionCardForm newTransactionCardForm) {
        log.info(">> resultAndValidTransaction [newTransactionCardForm = {}]", newTransactionCardForm.getNumberCard());
        Optional<Card> card = cardService.findByNumberCard(newTransactionCardForm.getNumberCard());
        log.info("<< resultAndValidTransaction [card = {}]", card);
        if(!card.isPresent()) {
            log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCardEnum.NON_EXISTING_CARD.getName());
            return Mono.just(ResultTransactionCardEnum.NON_EXISTING_CARD);
        } else if(!Objects.equals(newTransactionCardForm.getPasswordCard(), card.get().getPasswordCard())) {
            log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCardEnum.INVALID_PASSWORD.getName());
            return Mono.just(ResultTransactionCardEnum.INVALID_PASSWORD);
        } else if(card.get().getBalanceCard().compareTo(newTransactionCardForm.getBalanceCard()) < 0) {
            log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCardEnum.INSUFFICIENT_FUNDS.getName());
            return Mono.just(ResultTransactionCardEnum.INSUFFICIENT_FUNDS);
        }

        BigDecimal balanceUpdate = card.get().getBalanceCard().subtract(newTransactionCardForm.getBalanceCard());

        card.get().setBalanceCard(balanceUpdate);

        cardService.update(card.get());

        log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCardEnum.OK.getName());
        return Mono.just(ResultTransactionCardEnum.OK);
    }
}
