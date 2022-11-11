package br.com.miniautorization.models.mapper;

import br.com.miniautorization.models.dto.CardForm;
import br.com.miniautorization.models.dto.CardView;
import br.com.miniautorization.models.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapperImpl implements MapStructMapper<Card, CardView, CardForm> {

    @Override
    public CardView entityToView(Card card) {
        return CardView.builder()
                .id(card.getId())
                .numberCard(card.getNumberCard())
                .balanceCard(card.getBalanceCard())
                .build();
    }

    @Override
    public Card formToEntity(CardForm cardForm) {
        return Card.builder()
                .numberCard(cardForm.getNumberCard())
                .passwordCard(cardForm.getPasswordCard())
                .build();
    }

}
