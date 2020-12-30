package com.paymentdemo.controller;

import com.paymentdemo.model.Purchase;
import com.paymentdemo.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static com.paymentdemo.date.CustomDateUtility.getSqlDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {
    @MockBean
    PurchaseService customerService;
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

        Date purchaseDate = getSqlDate(2020, "December", 30);
        Purchase expectedPurchase = new Purchase(purchaseDate, "MarksAndSpencers", 40, 1, 1);
        when(customerService.save("MarksAndSpencers", 40, 1,1))
                .thenReturn(expectedPurchase);


        Map<String, Object> sessionattr = new HashMap<>();
        sessionattr.put("userinfo", "XXXXXXXX");

        MvcResult mvcResult = mockMvc.perform(post("/demo/purchase")
                .sessionAttrs(sessionattr)
                // All parameters we want to send to the REST request.
                .param("shop", "MarksAndSpencers")
                .param("cost", "40")
                .param("customerId", "1")
                .param("cardId", "1"))
                .andReturn();
        String responseString = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"purchaseId\":0,\"purchaseDate\":\"2020-12-30\",\"shop\":\"MarksAndSpencers\",\"cost\":40,\"customerId\":1,\"cardId\":1}", responseString);
    }
}
