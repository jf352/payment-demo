package com.paymentdemo.controller;

import com.paymentdemo.model.Customer;
import com.paymentdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/demo")
public class CustomerController extends AbstractController<Customer> {

    @Autowired
    private CustomerService customerService;

    /**
     * Adds a customer to the customer repository.
     *
     * @param firstName - The first name of the customer.
     * @param lastName - The surname of the customer.
     * @param year - The year in which the customer was born.
     * @param month - The month in which the customer was born.
     * @param day - The day in which the customer was born.
     * @return A JSON response entity representing the customer.
     */
    @PostMapping("/customer")
    public ResponseEntity<Customer> add(@RequestParam("year") int year,
                                        @RequestParam("month") String month,
                                        @RequestParam("day") int day,
                                        @RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName) {
        Customer customer = customerService.save(firstName, lastName, year, month, day);
        return constructResponseEntity(customer);
    }

    /**
     * Retrieves a customer based on the customer ID.
     * @param customerId - The customer ID to retrieve the customer information from.
     * @return - The customer.
     */
    @GetMapping("/customer")
    public ResponseEntity<Customer> get(@RequestParam("customerId") Integer customerId) {
        Customer customer = customerService.get(customerId);
        return constructResponseEntity(customer);
    }

    /**
     * Retrieves a customer based on a card ID.
     * @param cardId - The card ID to look up a customer from.
     * @return - The customer associated with the card ID.
     */
    @GetMapping("/customer/bycard")
    public ResponseEntity<Customer> getByCard(@RequestParam("cardId") Integer cardId) {
        Customer customer = customerService.getByCard(cardId);
        return constructResponseEntity(customer);
    }

    /**
     * Returns a customer based on a given purchase ID.
     * @param purchaseId - The ID of the purchase to use.
     * @return - The customer associated with a purchased.
     */
    @GetMapping("/customer/bypurchase")
    public ResponseEntity<Customer> getByPurchase(@RequestParam("purchaseId") Integer purchaseId) {
        Customer customer = customerService.getByPurchase(purchaseId);
        return constructResponseEntity(customer);
    }

    /**
     * Returns all customers registered to the system.
     * @return Every single customer registered with this system.
     */
    @GetMapping("/customer/all")
    public ResponseEntity<List<Customer>> getAll()
    {
        List<Customer> allCustomers = customerService.getAllCustomers();
        HttpHeaders headers = constructHeaders();
        if(!allCustomers.isEmpty())
        {
            return new ResponseEntity<>(allCustomers, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a customer associated with a customer ID.
     * @param customerId - The ID of the customer you wish to delete.
     * @return The customer which has been deleted.
     */
    @DeleteMapping("/customer")
    public ResponseEntity<Customer> delete(@RequestParam("customerId") Integer customerId) {
        Customer customer = customerService.delete(customerId);
        return constructResponseEntity(customer);
    }
}