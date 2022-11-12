package br.com.miniautorization.controller;

import br.com.miniautorization.models.dto.NewCardForm;
import br.com.miniautorization.models.entity.Card;
import br.com.miniautorization.repository.CardRepository;
import br.com.miniautorization.util.ConstantsDefaultValueUtils;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void checkMustCreateACard1() throws Exception {
        URI uri = new URI("/cartoes");

        Long numberCard = Long.parseLong(ConstantsDefaultValueUtils.generatedRandomNumber(10));
        Integer password = Integer.parseInt(ConstantsDefaultValueUtils.generatedRandomNumber(4));

        NewCardForm cardForm = NewCardForm.builder()
                .numberCard(numberCard)
                .passwordCard(password)
                .balanceCard(ConstantsDefaultValueUtils.default_balance_value)
                .build();

        String json = new Gson().toJson(cardForm);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void checkBalanceOfNewLyCreatedCard2() throws Exception {
        Card card = cardRepository.findTop1ByOrderByIdDesc();
        URI uri = new URI("/cartoes/" + card.getNumberCard());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));

        String result = resultActions.andReturn().getResponse().getContentAsString();

        Assert.assertTrue(new BigDecimal(result)
                .compareTo(ConstantsDefaultValueUtils.default_balance_value) == 0);
    }
}
