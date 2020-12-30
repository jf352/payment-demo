package com.paymentdemo.service;

import com.paymentdemo.model.Customer;
import com.paymentdemo.model.Purchase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BalanceService extends AbstractService {

    /**
     * Method to find the balance of a particular customer.
     * @param customerId The customer ID to get a balance from.
     * @return The balance of the customer.
     */
    public Integer getByCustomer(Integer customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isPresent())
        {
            int balance = 0;
            List<Purchase> purchaseStream = purchaseRepo.findAll()
                    .stream()
                    .filter(tr -> tr.getCustomerId() == customerId)
                    .collect(Collectors.toList());
            for(Purchase purchase : purchaseStream)
            {
                balance += purchase.getCost();
            }
            return balance;
        }
        return null;
    }
}
