package com.paymentdemo.controller;

import com.paymentdemo.service.BalanceService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BalanceController.class)
public class BalanceControllerTest {
    @MockBean
    BalanceService balanceService;
    @Autowired
    private MockMvc mockMvc;

    /**
     * Important to note this doesn't test the *Service* functionality, it tests
     * that the REST Controller API works.
     *
     * This will test that the basic GET functionality works, the only functionality we have in Balance controller.
     * @throws Exception - If something goes wrong during retrieval. This is generic to cover as many scenarios as can be.
     */
    @Test
    public void test_rest_functionality() throws Exception
    {
        when(balanceService.getByCustomer(1))
                .thenReturn(40);


        Map<String, Object> sessionattr = new HashMap<>();
        sessionattr.put("userinfo", "XXXXXXXX");

        MvcResult mvcResult = mockMvc.perform(get("/demo/balance")
                .sessionAttrs(sessionattr)
                // All parameters we want to send to the REST request.
                .param("customerId", "1"))
                .andReturn();
        String responseString = mvcResult.getResponse().getContentAsString();
        assertEquals("40", responseString);
    }
}
