package com.paymentdemo.service;

import com.paymentdemo.model.Card;
import com.paymentdemo.model.Customer;
import com.paymentdemo.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static com.paymentdemo.date.CustomDateUtility.getSqlDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = { "spring.datasource.url=jdbc:h2:mem:testdb" })
public class CardServiceTest {

    @Autowired
    private CardService cardService;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void cardServiceTest() {
        // We need a customer to exist first
        customerRepository.save(new Customer("James", "Forward", getSqlDate(1991, "July", 4)));
        // Check that the customer is correctly saved
        assertEquals(1L, customerRepository.count());
        // Now, create a new card - we're testing the cardService functionality here.
        cardService.save(1);

        // Check that the correct number of cards exist (1)
        List<Card> byCustomer = cardService.getByCustomer(1);
        assertEquals(1, byCustomer.size()); // We should have one card.
        // Get the first (and only!) card
        Card card = byCustomer.get(0);
        // Verify aspects of card.
        assertTrue(card.isValid());
        assertEquals(1, card.getCardId()); // First card - ID 0.
        assertEquals(1, card.getCustomerId()); // First customer - ID 0.

    }
}
