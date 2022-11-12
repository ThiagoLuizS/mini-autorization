package br.com.miniautorization.models.dto;

import br.com.miniautorization.util.ConstantsDefaultValueUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCardForm {

    @NotNull(message = "O número do cartão é obrigatório")
    private Long numberCard;

    @NotNull(message = "A senha do cartão é obrigatório")
    private Integer passwordCard;

    @JsonIgnore
    private BigDecimal balanceCard = ConstantsDefaultValueUtils.default_value;
}
