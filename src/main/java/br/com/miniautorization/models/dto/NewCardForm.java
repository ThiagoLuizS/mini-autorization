package br.com.miniautorization.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCardForm {

    @NotBlank(message = "O número do cartão é obrigatório")
    private Long numberCard;

    @Size(max = 4, message = "A senha do cartão deve ter no máximo 4 digitos")
    @NotBlank(message = "A senha do cartão é obrigatório")
    private Integer passwordCard;

}
