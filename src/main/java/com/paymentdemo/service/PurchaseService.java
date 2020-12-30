package com.paymentdemo.service;

import com.paymentdemo.model.Card;
import com.paymentdemo.model.Customer;
import com.paymentdemo.model.Purchase;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService extends AbstractService {


    public Purchase get(Integer purchaseId) {
        Optional<Purchase> card = purchaseRepo.findById(purchaseId);
        // For the demo, just returning a null if not found.
        return card.orElse(null);
    }

    /**
     * This allows us to delete a purchase, for whatever reason. Unlikely to be used in production.
     * @param purchaseId The purchase ID to delete.
     * @return An object representation of the deleted object.
     */
    public Purchase delete(Integer purchaseId) {
        Optional<Purchase> optionalPurchase = purchaseRepo.findById(purchaseId);
        Purchase potentialPurchase = null;
        if(optionalPurchase.isPresent()) {
            potentialPurchase = optionalPurchase.get();
            purchaseRepo.deleteById(purchaseId);
        }
        return potentialPurchase;
    }

    /**
     * Creates a purchase and saves it to the DB. Requires a valid customer & card ID to be successful,
     * otherwise will return a 404 message (in production).
     * @param shop A description of the shop (or vendor) the purchase was made at.
     * @param cost The cost of the purchase to the customer.
     * @param customerId The customer who initiated the purchase.
     * @param cardId The card the purchase is tied to.
     * @return An object representation of the saved purchase.
     */
    public Purchase save(String shop, int cost, int customerId, int cardId) {

        Optional<Customer> foundCustomer = customerRepo.findById(customerId);
        Optional<Card> foundCard = cardRepo.findById(cardId);
        Purchase proposedPurchase = null;
        // Will only create a purchase if both the card ID & customer ID exist (no point, otherwise it'll be
        // invalid).
        if(foundCustomer.isPresent() && foundCard.isPresent())
        {
            // Also checks if the card is valid (maybe frozen? or expired)
            if(foundCard.get().isValid())
            {
                Date purchaseDate = new Date(System.currentTimeMillis());
                proposedPurchase = new Purchase(purchaseDate, shop, cost, customerId, cardId);
                purchaseRepo.save(proposedPurchase);
            }
        }
        return proposedPurchase; // Return a null object if customer/card is not found, or card is invalid.
    }

    public List<Purchase> getAll() {
        return purchaseRepo.findAll();
    }
}
