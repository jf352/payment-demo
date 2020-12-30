package com.paymentdemo.service;

import com.paymentdemo.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = { "spring.datasource.url=jdbc:h2:mem:testdb" })
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void customerServiceTest() {
        // Save a customer.
        customerService.save("James", "Forward", 1991, "July", 4);
        // Check that there is indeed only one customer
        List<Customer> allCustomers = customerService.getAllCustomers();
        assertEquals(1, allCustomers.size());

        // Verify aspects of customer object
        Customer customer = allCustomers.get(0);
        assertEquals("James", customer.getFirstName());
        assertEquals("Forward", customer.getLastName());
        // Using deprecated methods but for unit testing should suffice as their meaning is clear.
        //noinspection deprecation
        assertEquals(91, customer.getBirthDate().getYear());
        //noinspection deprecation
        assertEquals(6, customer.getBirthDate().getMonth());
        //noinspection deprecation
        assertEquals(4, customer.getBirthDate().getDay());
    }
}
