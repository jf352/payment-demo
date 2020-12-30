package com.paymentdemo.controller;

import com.paymentdemo.model.Customer;
import com.paymentdemo.service.CustomerService;
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

import static com.paymentdemo.date.CustomDateUtility.getSqlDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @MockBean
    CustomerService customerService;
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

        Customer expectedCustomer = new Customer("James", "Forward", getSqlDate(1991, "July", 4));
        when(customerService.save("James", "Forward", 1991, "July", 4))
                .thenReturn(expectedCustomer);


        Map<String, Object> sessionattr = new HashMap<>();
        sessionattr.put("userinfo", "XXXXXXXX");

        MvcResult mvcResult = mockMvc.perform(post("/demo/customer")
                .sessionAttrs(sessionattr)
                // All parameters we want to send to the REST request.
                .param("firstName", "James")
                .param("lastName", "Forward")
                .param("year", "1991")
                .param("month", "July")
                .param("day", "4"))
                .andReturn();
        String responseString = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"customerId\":0,\"birthDate\":\"1991-07-04\",\"firstName\":\"James\",\"lastName\":\"Forward\"}", responseString);
    }
}
