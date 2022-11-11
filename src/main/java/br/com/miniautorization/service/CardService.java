package br.com.miniautorization.service;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.models.dto.NewCardView;
import br.com.miniautorization.models.entity.Card;
import br.com.miniautorization.models.mapper.CardMapperImpl;
import br.com.miniautorization.models.mapper.MapStructMapper;
import br.com.miniautorization.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class CardService extends AbstractService<Card, NewCardView, NewCardForm> {

    private final CardRepository cardRepository;
    private final CardMapperImpl cardMapper;

    public Optional<NewCardView> findByCardViewForNumber(Long numberCard) {
        log.info(">> findByCardViewForNumber [numberCard={}]", numberCard);
        Optional<Card> card = findByNumberCard(numberCard);
        log.info("<< findByCardViewForNumber [card={}]", card);
        Optional<NewCardView> view = card.map(cardMapper::entityToView);;
        return view;
    }

    public Optional<Card> findByNumberCard(Long numberCard) {
        log.info(">> findByNumberCard [numberCard={}]", numberCard);
        Optional<Card> card = cardRepository.findByNumberCard(numberCard);
        log.info("<< findByNumberCard [card={}]", card);
        return card;
    }

    public void update(Card card) {
        cardRepository.save(card);
    }

    @Override
    protected JpaRepository<Card, Long> getRepository() {
        return cardRepository;
    }

    @Override
    protected MapStructMapper<Card, NewCardView, NewCardForm> getConverter() {
        return cardMapper;
    }
}
