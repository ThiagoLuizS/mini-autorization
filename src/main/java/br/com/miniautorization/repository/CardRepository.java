package br.com.miniautorization.repository;

import br.com.miniautorization.models.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumberCard(Long numberCard);
    Card findTop1ByOrderByIdDesc();
}
