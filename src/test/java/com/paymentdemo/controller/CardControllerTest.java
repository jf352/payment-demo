package com.paymentdemo.controller;

import com.paymentdemo.model.Card;
import com.paymentdemo.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CardController.class)
public class CardControllerTest {
    @MockBean
    CardService cardService;
    @Autowired
    private MockMvc mockMvc;

    /**
     * Important to note this doesn't test the *Service* functionality, it tests
     * that the REST Controller API works.
     *
     * This will test that the basic POST functionality works, which will prove that the Web Layer is complete,
     * without having to repeat the test for other maps (GET for example).
     * @throws Exception - If something goes wrong during retrieval. This is generic to cover as many scenarios as can be.
     */
    @Test
    public void test_rest_functionality() throws Exception
    {

        Card expectedCard = new Card(1);
        when(cardService.save(1))
                .thenReturn(expectedCard);


        Map<String, Object> sessionattr = new HashMap<>();
        sessionattr.put("userinfo", "XXXXXXXX");

        MvcResult mvcResult = mockMvc.perform(post("/demo/card")
                .sessionAttrs(sessionattr)
                // All parameters we want to send to the REST request.
                .param("customerId", "1"))
                .andReturn();
        String responseString = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"cardId\":0,\"customerId\":1,\"valid\":true}", responseString);
    }
}
