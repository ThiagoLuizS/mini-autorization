package br.com.miniautorization.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_card")
public class Card {

    @Id
    @Column(name = "id_card")
    @SequenceGenerator(name = "seq_id_card", sequenceName = "seq_id_card", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_id_card")
    private Long id;

    @NotNull
    @Column(name = "number_card")
    private Long numberCard;

    @NotNull
    @Column(name = "balance_card")
    private BigDecimal balanceCard;

    @NotBlank
    @Column(name = "password_card")
    private Integer passwordCard;

}
