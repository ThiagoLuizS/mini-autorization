package br.com.miniautorization.service;

import br.com.miniautorization.models.dto.CardForm;
import br.com.miniautorization.models.dto.CardView;
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

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class CardService extends AbstractService<Card, CardView, CardForm> {

    private final CardRepository cardRepository;
    private final CardMapperImpl cardMapper;

    @Override
    protected JpaRepository<Card, Long> getRepository() {
        return cardRepository;
    }

    @Override
    protected MapStructMapper<Card, CardView, CardForm> getConverter() {
        return cardMapper;
    }
}
