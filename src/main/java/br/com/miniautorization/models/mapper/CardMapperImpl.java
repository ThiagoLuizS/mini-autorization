package br.com.miniautorization.models.mapper;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.models.dto.NewCardView;
import br.com.miniautorization.models.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapperImpl implements MapStructMapper<Card, NewCardView, NewCardForm> {

    @Override
    public NewCardView entityToView(Card card) {
        return NewCardView.builder()
                .numberCard(card.getNumberCard())
                .balanceCard(card.getBalanceCard())
                .build();
    }

    @Override
    public Card formToEntity(NewCardForm cardForm) {
        return Card.builder()
                .numberCard(cardForm.getNumberCard())
                .balanceCard(cardForm.getBalanceCard())
                .passwordCard(cardForm.getPasswordCard())
                .build();
    }

}
