package com.paymentdemo.service;

import com.paymentdemo.model.Card;
import com.paymentdemo.model.Customer;
import com.paymentdemo.model.Purchase;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.paymentdemo.date.CustomDateUtility.getSqlDate;

@Service
public class CustomerService extends AbstractService {

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer get(Integer customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        // For the demo, just returning a null if not found.
        return customer.orElse(null);
    }

    public Customer save(String firstName, String lastName, int year, String monthString, int day) {

        Date birthDate = getSqlDate(year, monthString, day);
        // Creating the customer object using the value above.
        Customer customer = new Customer(firstName, lastName, birthDate);
        // Saving the customer to the customer repository.
        customerRepo.save(customer);
        return customer;
    }

    public Customer delete(Integer customerId) {
        Optional<Customer> foundCustomer = customerRepo.findById(customerId);
        Customer potentialCustomer = null;
        if(foundCustomer.isPresent())
        {
            potentialCustomer = foundCustomer.get();
            customerRepo.deleteById(customerId);
        }
        return potentialCustomer; // If customer isn't present, a NOT FOUND error will be returned.
    }

    public Customer getByCard(Integer cardId) {
        Optional<Card> optionalCard = cardRepo.findById(cardId);
        Customer potentialCustomer = null; // Customer is null by default; will be returned with a real value if found.
        if(optionalCard.isPresent())
        {
            Card foundCard = optionalCard.get();
            int customerId = foundCard.getCustomerId();
            potentialCustomer = get(customerId); // If there really is a customer, set potentialCustomer to it.
        }
        return potentialCustomer; // If card isn't present, this will simply return a NOT FOUND error.
    }

    /**
     * Method to find a customer by a purchase ID.
     * @param purchaseId - The transactino ID to find the customer by
     * @return - The customer who initiated a specific purchase.
     */
    public Customer getByPurchase(Integer purchaseId) {
        Optional<Purchase> optionalPurchase = purchaseRepo.findById(purchaseId);
        Customer potentialCustomer = null; // Customer is null by default; will be returned with a real value if found.
        if(optionalPurchase.isPresent())
        {
            Purchase foundPurchase = optionalPurchase.get();
            int customerId = foundPurchase.getCustomerId();
            potentialCustomer = get(customerId);
        }
        return potentialCustomer; // If purchase isn't present, this will simply return a NOT FOUND error.
    }
}
