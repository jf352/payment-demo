package com.paymentdemo.controller;

import com.paymentdemo.model.Purchase;
import com.paymentdemo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/demo")
public class PurchaseController extends AbstractController<Purchase> {

    @Autowired
    private PurchaseService purchaseService;

    /**
     * Adds a new purchase into the system.
     * @param shop The name/description of a shop that the purchase is associated with.
     * @param cost The cost of the purchase.
     * @param customerId The customer who initiated the purchase.
     * @param cardId The card associated with the purchase.
     * @return An object representation of the purchase.
     */
    @PostMapping("/purchase")
    public ResponseEntity<Purchase> add(@RequestParam("shop") String shop,
                                        @RequestParam("cost") int cost,
                                        @RequestParam("customerId") int customerId,
                                        @RequestParam("cardId") int cardId) {
        Purchase purchase = purchaseService.save(shop, cost, customerId, cardId);
        return constructResponseEntity(purchase);
    }

    /**
     * Retrieves a purchase based on its primary key.
     * @param purchaseId - The ID of the purchase
     * @return The purchase object which holds information about the purchase.
     */
    @GetMapping("/purchase")
    public ResponseEntity<Purchase> get(@RequestParam("purchaseId") Integer purchaseId) {
        Purchase purchase = purchaseService.get(purchaseId);
        return constructResponseEntity(purchase);
    }

    /**
     * Simple method to return all purchases.
     * @return A list of every purchase on the system.
     */
    @GetMapping("/purchase/all")
    public ResponseEntity<List<Purchase>> getAll()
    {
        List<Purchase> allPurchases = purchaseService.getAll();
        HttpHeaders headers = constructHeaders();
        if(!allPurchases.isEmpty())
        {
            return new ResponseEntity<>(allPurchases, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a purchase. Doubt this would ever be used in a real-world scenario.
     * @param purchaseId - The purchase to delete.
     * @return A representative object of the purchase.
     */
    @DeleteMapping("/purchase")
    public ResponseEntity<Purchase> delete(@RequestParam("purchaseId") Integer purchaseId) {
        Purchase purchase = purchaseService.delete(purchaseId);
        return constructResponseEntity(purchase);
    }
}