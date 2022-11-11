package br.com.miniautorization.service;

import br.com.miniautorization.models.dto.NewTransactionCardForm;
import br.com.miniautorization.models.entity.Card;
import br.com.miniautorization.models.enumerators.ResultTransactionCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CardService cardService;

    public ResultTransactionCard resultAndValidTransaction(NewTransactionCardForm newTransactionCardForm) {
        log.info(">> resultAndValidTransaction [newTransactionCardForm = {}]", newTransactionCardForm.getNumberCard());
        Optional<Card> card = cardService.findByNumberCard(newTransactionCardForm.getNumberCard());
        log.info("<< resultAndValidTransaction [card = {}]", card);
        if(!card.isPresent()) {
            log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCard.NON_EXISTING_CARD.getName());
            return ResultTransactionCard.NON_EXISTING_CARD;
        } else if(!Objects.equals(newTransactionCardForm.getPasswordCard(), card.get().getPasswordCard())) {
            log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCard.INVALID_PASSWORD.getName());
            return ResultTransactionCard.INVALID_PASSWORD;
        } else if(card.get().getBalanceCard().compareTo(newTransactionCardForm.getBalance()) < 0) {
            log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCard.INSUFFICIENT_FUNDS.getName());
            return ResultTransactionCard.INSUFFICIENT_FUNDS;
        }

        BigDecimal balanceUpdate = card.get().getBalanceCard().subtract(newTransactionCardForm.getBalance());

        card.get().setBalanceCard(balanceUpdate);

        cardService.update(card.get());

        log.info("<< resultAndValidTransaction [ResultTransactionCard = {}]", ResultTransactionCard.OK.getName());
        return ResultTransactionCard.OK;
    }
}
