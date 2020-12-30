package com.paymentdemo.service;

import com.paymentdemo.model.Card;
import com.paymentdemo.model.Customer;
import com.paymentdemo.repository.CardRepository;
import com.paymentdemo.model.Purchase;
import com.paymentdemo.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static com.paymentdemo.date.CustomDateUtility.getSqlDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = { "spring.datasource.url=jdbc:h2:mem:testdb" })
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CardRepository cardRepository;

    @Test
    public void purchaseServiceTest() {
        // We need a customer to exist first
        customerRepository.save(new Customer("James", "Forward", getSqlDate(1991, "July", 4)));
        // Check that the customer is correctly saved
        assertEquals(1L, customerRepository.count());
        // We need a card to exist first as well. Should be customer ID 1, as per convention
        cardRepository.save(new Card(1));
        // Check that the card is correctly saved
        assertEquals(1L, cardRepository.count());
        // Because purchase service currently uses "now" to save, we want to get the SimpleDateFormat version now
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todaysDateFormatted = formatter.format(new Date(System.currentTimeMillis()));
        // Now create a new purchasing object...
        purchaseService.save("Marks and Spencers", 40, 1, 1);
        // Retrieve the purchase object from memory...
        Purchase purchase = purchaseService.get(1);
        assertEquals(1, purchase.getCardId());
        assertEquals(1, purchase.getCustomerId());
        assertEquals(40, purchase.getCost());
        assertEquals(todaysDateFormatted, formatter.format(purchase.getPurchaseDate()));
        assertEquals(1, purchase.getPurchaseId());
        assertEquals("Marks and Spencers", purchase.getShop());
    }
}
