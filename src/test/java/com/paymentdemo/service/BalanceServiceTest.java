package com.paymentdemo.service;

import com.paymentdemo.model.Card;
import com.paymentdemo.model.Customer;
import com.paymentdemo.repository.CardRepository;
import com.paymentdemo.model.Purchase;
import com.paymentdemo.repository.CustomerRepository;
import com.paymentdemo.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.paymentdemo.date.CustomDateUtility.getSqlDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = { "spring.datasource.url=jdbc:h2:mem:testdb" })
public class BalanceServiceTest {

    @Autowired
    private BalanceService balanceService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    public void balanceServiceTest() {
        // We need a customer to exist first
        customerRepository.save(new Customer("James", "Forward", getSqlDate(1991, "July", 4)));
        // Check that the customer is correctly saved
        assertEquals(1L, customerRepository.count());
        // We need a card to exist first as well. Should be customer ID 1, as per convention
        cardRepository.save(new Card(1));
        // Check that the card is correctly saved
        assertEquals(1L, cardRepository.count());
        // We need a purchase tied to above card and customer as well
        purchaseRepository.save(new Purchase(getSqlDate(1991, "July", 4), "Marks and Spencers", 40, 1, 1));
        // Now we can check the balance of the customer.
        Integer customerBalance = balanceService.getByCustomer(1);
        assertEquals(40, customerBalance);
    }
}
