package br.com.miniautorization.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCardView {
    private Long numberCard;
    private BigDecimal balanceCard;
}
